function new_xyz = mooring(OPTIONS, probes, rotors)
%% run the mooring model to calculate new positions

% coordinates at points along the mooring lines, and the buoyancy pods (plus anything else not a turbine)
xyz_probes = probes.xyz;
% coordinates of turbines (center of rotor)
xyz_rotors = rotors.data(:,2:4);

% for now just move the coordinates randomly, to demostrate the code works
new_xyz.probes = xyz_probes + 5*rand(size(xyz_probes));
new_xyz.rotors = xyz_rotors + 5*rand(size(xyz_rotors));

end % function