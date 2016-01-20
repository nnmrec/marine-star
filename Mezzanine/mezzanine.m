%% START-UP
% add any dependencies to the path
addpath([pwd filesep 'utilities' filesep 'mooring']);
addpath([pwd filesep 'utilities' filesep 'CsvWriter']);

%% some bookkeeping
dir_input      = [pwd filesep 'inputs'];
dir_output     = [pwd filesep 'outputs'];
fileIn_probes  = [dir_input filesep 'probes.csv'];
fileIn_rotors  = [dir_input filesep 'rotors.csv'];             
fileOut_probes = [dir_output filesep 'probes-velocity.csv'];
fileOut_rotors = [dir_output filesep 'rotors-velocity.csv'];  
fileOut_thrust = [dir_output filesep 'rotors-thrust.csv'];  
fileOut_torque = [dir_output filesep 'rotors-torque.csv'];  
      
%% load the options for this case 
run([dir_input filesep 'options_Mezzanine.m'])
% run([dir_input filesep 'options_BamfieldFlume.m'])


%% write the initial conditions to "input files" for the CFD model  
% construct the cell array
probes_vars = {'ProbeName','x', 'y','z'};
probes_data = horzcat(probes.names, num2cell(probes.xyz));
P           = vertcat(probes_vars, probes_data);
rotors_vars = {'name','table','rotor_rpm','x','y','z','nx','ny','nz','rotor_radius','hub_radius','rotor_thick'};
rotors_data = horzcat(rotors.names, rotors.tables, num2cell(rotors.data));
R           = vertcat(rotors_vars, rotors_data);
% write to CSV file
f = CsvWriter(fileIn_probes,'delimiter',',');
f.append(P);
f.close();
f = CsvWriter(fileIn_rotors,'delimiter',',');
f.append(R);
f.close();

%% run the CFD model for first time
% run STAR-CCM+
%if runOnHPC
%    system('./submit-job-Hyak.sh');
%else
    system(['cp ' starSimFile ' runs.' starSimFile]);
    system(['rm log.' starSimFile]);
    system(['starccm+ -batch macros/main.java -np ' num2str(nCPUs) ' -licpath 1999@flex.cd-adapco.com -power -podkey $myPODkey -batch-report runs.' starSimFile ' 2>&1 | tee log.' starSimFile]);
%end


couplingIters = 1;
for k = 1:couplingIters
    %% read the "output files" of the CFD model

    % PROBES: read/process the file header
    fid = fopen(fileOut_probes, 'r');
    header = fgetl(fid);
    fclose(fid);
    cols        = strsplit(header,',');
    probes_name = cell(size(cols,2)-1, 1);
    for n = 2:size(cols,2)
        token            = strtok(cols(n), ':');
        var              = token{1}(2:end);
        probes_name{n-1} = var;
    end
    % read the data section
    probes.vel = csvread(fileOut_probes,1);
    probes.vel = probes.vel(end,2:end)';    % only keep the last iteration
    % actually this appears to be in proper order! yay!
    % horzcat(probes_name, num2cell(probes.vel))


    % ROTOR INFLOW SPEEDS: read/process the file header
    fid = fopen(fileOut_rotors, 'r');
    header = fgetl(fid);
    fclose(fid);
    cols        = strsplit(header,',');
    rotors_name = cell(size(cols,2)-1, 1);
    for n = 2:size(cols,2)
        token            = strtok(cols(n), '}');
        [token, remain]  = strtok(token, '{');
        rotors_name{n-1} = remain{1}(2:end);
    end
    % read the data section
    rotors.vel = csvread(fileOut_rotors,1);
    rotors.vel = rotors.vel(end,2:end)';    % only keep last iteration
    % actually this appears to be in proper order! yay!
    % horzcat(rotors_name, num2cell(rotors.vel))
    
    % ROTOR THRUST: read/process the file header
    fid = fopen(fileOut_thrust, 'r');
    header = fgetl(fid);
    fclose(fid);
    cols        = strsplit(header,',');
    rotors_name = cell(size(cols,2)-1, 1);
    for n = 2:size(cols,2)
        token            = strtok(cols(n), '}');
        [token, remain]  = strtok(token, '{');
        rotors_name{n-1} = remain{1}(2:end);
    end
    % read the data section
    rotors.thrust = csvread(fileOut_thrust,1);
    rotors.thrust = rotors.thrust(end,2:end)';    % only keep last iteration
    % actually this appears to be in proper order! yay!
    % horzcat(rotors_name, num2cell(rotors.thrust))
    
    % ROTOR TORQUE: read/process the file header
    fid = fopen(fileOut_torque, 'r');
    header = fgetl(fid);
    fclose(fid);
    cols        = strsplit(header,',');
    rotors_name = cell(size(cols,2)-1, 1);
    for n = 2:size(cols,2)
        token            = strtok(cols(n), '}');
        [token, remain]  = strtok(token, '{');
        rotors_name{n-1} = remain{1}(2:end);
    end
    % read the data section
    rotors.torque = csvread(fileOut_torque,1);
    rotors.torque = rotors.torque(end,2:end)';    % only keep last iteration
    % actually this appears to be in proper order! yay!
    % horzcat(rotors_name, num2cell(rotors.torque))


    %% run the mooring model to calculate new positions
    new_xyz = mooring(OPTIONS, probes, rotors);


    %% compute the new rotor speed based on the updated inflow speeds
    new_rpm = zeros(numel(rotors.names),1);
    for n = 1:size(rotors.tables, 1)

        % read wind and rpm from the table
        file_perf = [dir_input filesep 'tables' filesep rotors.tables{n} '.csv'];

        perf_table = csvread(file_perf,1);
        wind = perf_table(:,1);
        rpm  = perf_table(:,4);

        new_rpm(n) = interp1(wind, rpm, rotors.vel(n));

    end


    %% write the new coordinates/settings to file (for the next iteration of the CFD model)
    % construct the cell array
    probes_vars = {'ProbeName','x', 'y','z'};
    probes_data = horzcat(probes.names, num2cell(new_xyz.probes));
    P           = vertcat(probes_vars, probes_data);
    rotors_vars = {'name','table','rotor_rpm','x','y','z','nx','ny','nz','rotor_radius','hub_radius','rotor_thick'};
    rotors_data = horzcat(rotors.names, rotors.tables, num2cell(new_rpm), num2cell(new_xyz.rotors), num2cell(rotors.data(:,5:10)));
    R           = vertcat(rotors_vars, rotors_data);
    % write to CSV file
    f = CsvWriter(fileIn_probes,'delimiter',',');
    f.append(P);
    f.close();
    f = CsvWriter(fileIn_rotors,'delimiter',',');
    f.append(R);
    f.close();


    %% now can run STAR-CCM+ again with updated inputs
    if runOnHPC
        system('./submit-job-Hyak_updated.sh');
    else
        system(['starccm+ -batch macros/updateAndRun.java -np ' num2str(nCPUs) ' -licpath 1999@flex.cd-adapco.com -power -podkey $myPODkey -batch-report runs.' starSimFile ' 2>&1 | tee log.' starSimFile]);
    end
    
end



