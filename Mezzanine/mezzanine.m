%% function mezzanine(optionsFile)
% optionsFile = 'options.Mezzanine_small_propeller.m';      % name of options file (this is echoed into "options.m")
optionsFile = 'options.Mezzanine_small.m';      % name of options file (this is echoed into "options.m")
% optionsFile = 'options.Mezzanine.m';            % name of options file (this is echoed into "options.m")

%% STARTUP - add any dependencies to the path
addpath(genpath([pwd filesep 'utilities']));

% read the main user options (read/initialize "user inputs" ... note: some of the java macros also have "user inputs")
[OPTIONS,filesIO,probes,rotors] = initMain(optionsFile);

%% WRITE the initial conditions to "input files" for the CFD model 
writeInputsProbes(filesIO,probes);
writeInputsRotors(filesIO,rotors);

%% RUN the CFD model for first time
OPTIONS = runSTARCCM(OPTIONS,'main.java');

%% READ the output from the CFD model
[probes, rotors] = readOutputs(filesIO,probes,rotors);
        
%% RE-MESH Adaptive-Mesh-Refinement (AMR) ... should only need this on the first iteration of the CFD model; because movement from mooring model is expected to be "small"
% runSTARCCM(OPTIONS,'remeshAMR.java');


%% ITERATE between running the CFD and Mooring models
for n = 1:OPTIONS.nUpdateMooring
    
    % RUN STAR-CCM+, and as the velocity field update ... the rotor speed
    % should also update to perform at target tip-speed-ratio, this call
    % also updates the positions of the rotors (from the mooring model)
    [rotors] = adjustRotorSpeeds(OPTIONS,filesIO,rotors);
    
    % READ the output from the CFD model
    [probes, rotors] = readOutputs(filesIO,probes,rotors);
    
    % RUN the MOORING model to calculate new positions after the CFD model has "converged"
    [probes, rotors] = mooring(OPTIONS, probes, rotors);
    
    % WRITE the updated xyz coordinates for next iteration of CFD model
    writeInputsProbes(filesIO,probes);
    writeInputsRotors(filesIO,rotors);

end

