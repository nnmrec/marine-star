%% STARTUP - add any dependencies to the path and read/initialize "user inputs"
addpath(genpath([pwd filesep 'utilities']));

% read the main user options (note: some of the java macros also have "user inputs")
[OPTIONS,filesIO,probes,rotors] = initMain();

%% WRITE the initial conditions to "input files" for the CFD model 
writeInputsProbes(filesIO,probes);
writeInputsRotors(filesIO,rotors);

%% RUN the CFD model for first time (should also run the Adaptive-Mesh-Refinement here)
runSTARCCM(OPTIONS,'main.java');

%% READ the output from the CFD model
[probes, rotors] = readOutputs(filesIO,probes,rotors);
        
%% ITERATE between running the CFD and Mooring models
for k = 1:OPTIONS.nUpdateMooring
    
    % RUN a number of inner loops to update the rotor speed based on updated/iterating inflow velocity
    for n = 1:OPTIONS.nUpdateRPM   
        switch OPTIONS.control
            case 'RPM'
                % compute new rotor speed and write new inputs files with updated rotor speeds
                rotors = updateRotorSpeeds(OPTIONS,filesIO,rotors);
                
                % need to update the stopping criteria
%                 tol = tol / 2;
                
                % now can run STAR-CCM+ again with updated inputs
                runSTARCCM(OPTIONS,'updateAndRun.java');

            case 'TSR'

            case 'none'

            otherwise
                error('ERROR: unrecognized option for control.  why you do that?');
        end
%%DEBUG
hold on; plot(n, rotors.data(:,1), 'o-r')
    end

    % READ the output from the CFD model
    [probes, rotors] = readOutputs(filesIO,probes,rotors);
    
    % RUN the MOORING model to calculate new positions after the CFD model has "converged"
    new_xyz = mooring(OPTIONS, probes, rotors);
    
    % WRITE the updated xyz coordinates for next iteration of CFD model
    probes.xyz         = new_xyz.probes;
    rotors.data(:,2:4) = new_xyz.rotors;
    writeInputs(filesIO,probes,rotors);

end



    




