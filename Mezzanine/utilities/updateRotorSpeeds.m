function [rotors] = updateRotorSpeeds(OPTIONS,filesIO,rotors)
%updateRotorSpeeds uses the last known rotor speed from STAR-CCM+ and then updates according to the input table
%   Detailed explanation goes here

% compute the new rotor speed based on the updated inflow speeds
new_rpm = zeros(numel(rotors.names),1);
for n = 1:size(rotors.tables, 1)
    % read wind and rpm from the file table
    file_perf  = [filesIO.dir_input filesep 'tables' filesep rotors.tables{n} '.csv'];
    perf_table = csvread(file_perf,1);
    wind       = perf_table(:,1);
    rpm        = perf_table(:,4);
    new_rpm(n) = interp1(wind, rpm, rotors.vel(n));
end
rotors.data(:,1) = new_rpm;

% write the new coordinates/settings to file (for the next iteration of the CFD model)
writeInputsRotors(filesIO,rotors);
    
end

