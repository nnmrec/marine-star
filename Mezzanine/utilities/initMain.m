function [OPTIONS,filesIO,probes,rotors] = initMain()
%initMain read the user inputs, and does some bookkeeping about file I/O
%   Detailed explanation goes here


%% some bookkeeping
filesIO.dir_input      = [pwd filesep 'inputs'];
filesIO.dir_output     = [pwd filesep 'outputs'];
filesIO.fileIn_probes  = [filesIO.dir_input filesep 'probes.csv'];
filesIO.fileIn_rotors  = [filesIO.dir_input filesep 'rotors.csv'];             
filesIO.fileOut_probes = [filesIO.dir_output filesep 'probes-velocity.csv'];
filesIO.fileOut_rotors = [filesIO.dir_output filesep 'rotors-velocity.csv'];  
filesIO.fileOut_thrust = [filesIO.dir_output filesep 'rotors-thrust.csv'];  
filesIO.fileOut_torque = [filesIO.dir_output filesep 'rotors-torque.csv'];  
      
%% load the options for this case
run([filesIO.dir_input filesep 'options.m'])
% run([dir_input filesep 'options_Mezzanine.m'])
% run([dir_input filesep 'options_BamfieldFlume.m'])

%% clean-up any auto-generated files (if they are too big to track in git)
system(['rm log.' OPTIONS.starSimFile]);
system('rm runs.*');

end

