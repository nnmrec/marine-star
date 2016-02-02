function [probes, rotors] = mooring(OPTIONS, probes, rotors)


%% reshape the data into column vectors, the format that mooring code prefers
% coordinates at points along the mooring lines, and the buoyancy pods (plus anything else not a turbine)
xyz_probes = probes.xyz;
vel_probes = probes.vel;

% coordinates of turbines (center of rotor)
xyz_rotors = rotors.data(:,2:4);
ang_rotors = rotors.data(:,5:7);
vel_rotors = rotors.vel;
thr_rotors = rotors.thrust;
tor_rotors = rotors.torque;

% positions: body nodes and then append line nodes: [x y z nx ny nz, x y z nx ny nz, ..., x y z, x y z, ...]
q_body = [];
q_line = [];

% velocities: body nodes and then append line nodes: [Ux Uy Uz, Ux Uy Uz, ..., Ux Uy Uz, Ux Uy Uz, ...]
v_body = [];
v_line = [];

% forces and moments: body nodes: [Fx Fy Fz Mx My Mz, Fx Fy Fz Mx My Mz, ... , Fx Fy Fz, Fx Fy Fz, ...]
f_body = [];
f_line = [];

% append (not as efficient because growing inside the loop, but easy)
nBodies = size(xyz_rotors,1);
for n = 1:nBodies
    q_body = [q_body; xyz_rotors(n,1)]; % x
    q_body = [q_body; xyz_rotors(n,2)]; % y
    q_body = [q_body; xyz_rotors(n,3)]; % z
    
    v_body = [v_body; vel_rotors(n)];   % "reference" inflow velocity
    
    f_body = [f_body; thr_rotors(n)];   % Fx
    f_body = [f_body; 0];               % Fy
    f_body = [f_body; 0];               % Fz
    f_body = [f_body; 0];               % Mx
    f_body = [f_body; 0];               % My
    f_body = [f_body; tor_rotors];      % Mz
end
nLines = size(xyz_probes,1);
for n = 1:nLines
    q_line = [q_line; xyz_probes(n,1)]; % x
    q_line = [q_line; xyz_probes(n,2)]; % y
    q_line = [q_line; xyz_probes(n,3)]; % z
    
    v_line = [v_line; vel_probes(n)];   % "reference" inflow velocity
    
    f_line = [f_line; 0];               % Fx (Tyler: you will need to add your drag force calculations here, I imagine some angles will give X, Y, and Z force components)
    f_line = [f_line; 0];               % Fy
    f_line = [f_line; 0];               % Fz
end

% append bodies and lines
q = [q_body; q_line];
v = [v_body; v_line];
f = [f_body; f_line];

%% run the equilibrium mooring model to calculate new positions
% new_xyz = mooring_equilibrium_solver(OPTIONS, q, v, f);

% for now just move the coordinates randomly, to demostrate the code "works"
% new_xyz.probes = xyz_probes;
% new_xyz.rotors = xyz_rotors;
new_xyz.probes = xyz_probes + 1*rand(size(xyz_probes));
new_xyz.rotors = xyz_rotors + 1*rand(size(xyz_rotors));


%% collect the output
probes.xyz         = new_xyz.probes;
rotors.data(:,2:4) = new_xyz.rotors;


end % function