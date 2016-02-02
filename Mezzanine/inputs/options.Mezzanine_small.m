%% set the initial conditions for the "Mezzanine"
OPTIONS.starSimFile      = 'Mezzanine_v2.sim';          % name of empty STAR-CCM+ sim file
OPTIONS.runOnHPC         = true;                        % option to run a PBS script for HPC systems (like Hyak), or run locally
OPTIONS.nCPUs            = 16;                          % number of CPU cores to run in parallel (check that it matches your PBS submit job script)
OPTIONS.control          = 'RPM';                       % choose 'RPM' for rotor speed control.  choose 'TSR' for local tip-speed-ratio control
OPTIONS.nUpdateRPM       = 3;                           % number of inner loops to update the rotor speed based on inflow velocity, should be 1 or greater (this options is probably dependent on max iterations of RANS model)
OPTIONS.nUpdateMooring   = 2;                           % number of times to iterate between CFD and Mooring models
OPTIONS.max_iter         = 500;                       	% max number of iterations in CFD model (should be able to estimate this based on CFL number and length of domain, and mesh size)
OPTIONS.limit_continuity = 1e-5;                        % convergence threshold for continuity

% coordinates of turbines (center of rotor), and other properties
rotors.names  = {'row-1_turbine-1'; ...
                 'row-1_turbine-2'; ...
                 'row-1_turbine-3'; ...
                 'row-1_turbine-4'; ...
                 'row-1_turbine-5'; ...
                 'row-2_turbine-1'; ...
                 'row-2_turbine-2'; ...
                 'row-2_turbine-3'; ...
                 'row-2_turbine-4'; ...
%                  'row-3_turbine-1'; ...
%                  'row-3_turbine-2'; ...
%                  'row-3_turbine-3'; ...
%                  'row-3_turbine-4'; ...
%                  'row-3_turbine-5'; ...
%                  'row-4_turbine-1'; ...
%                  'row-4_turbine-2'; ...
%                  'row-4_turbine-3'; ...
%                  'row-4_turbine-4'; ...
%                  'row-5_turbine-1'; ...
%                  'row-5_turbine-2'; ...
%                  'row-5_turbine-3'; ...
%                  'row-5_turbine-4'; ...
%                  'row-5_turbine-5'; ...
%                  'row-6_turbine-1'; ...
%                  'row-6_turbine-2'; ...
%                  'row-6_turbine-3'; ...
%                  'row-6_turbine-4' ...
                 };


rotors.tables = {'Mezzanine'; ...
                 'Mezzanine'; ...
                 'Mezzanine'; ...
                 'Mezzanine'; ...
                 'Mezzanine'; ...
                 'Mezzanine'; ...
                 'Mezzanine'; ...
                 'Mezzanine'; ...
                 'Mezzanine'; ...
%                  'Mezzanine'; ...
%                  'Mezzanine'; ...
%                  'Mezzanine'; ...
%                  'Mezzanine'; ...
%                  'Mezzanine'; ...
%                  'Mezzanine'; ...
%                  'Mezzanine'; ...
%                  'Mezzanine'; ...
%                  'Mezzanine'; ...
%                  'Mezzanine'; ...
%                  'Mezzanine'; ...
%                  'Mezzanine'; ...
%                  'Mezzanine'; ...
%                  'Mezzanine'; ...
%                  'Mezzanine'; ...
%                  'Mezzanine'; ...
%                  'Mezzanine'; ...
%                  'Mezzanine' ...
                 };

% rotor_rpm,x,y,z,nx,ny,nz,rotor_radius,hub_radius,rotor_thick
rotors.data = [9.2,187.50,100.0,30.0,1,0,0,12.5,0,2; ...
               9.2,187.50,175.0,30.0,1,0,0,12.5,0,2; ...
               9.2,187.50,250.0,30.0,1,0,0,12.5,0,2; ...
               9.2,187.50,325.0,30.0,1,0,0,12.5,0,2; ...
               9.2,187.50,400.0,30.0,1,0,0,12.5,0,2; ...
               9.2,375.00,137.5,30.0,1,0,0,12.5,0,2; ...
               9.2,375.00,212.5,30.0,1,0,0,12.5,0,2; ...
               9.2,375.00,287.5,30.0,1,0,0,12.5,0,2; ...
               9.2,375.00,362.5,30.0,1,0,0,12.5,0,2; ...
%                9.2,562.50,100.0,30.0,1,0,0,12.5,0,2; ...
%                9.2,562.50,175.0,30.0,1,0,0,12.5,0,2; ...
%                9.2,562.50,250.0,30.0,1,0,0,12.5,0,2; ...
%                9.2,562.50,325.0,30.0,1,0,0,12.5,0,2; ...
%                9.2,562.50,400.0,30.0,1,0,0,12.5,0,2; ...
%                9.2,750.00,137.5,30.0,1,0,0,12.5,0,2; ...
%                9.2,750.00,212.5,30.0,1,0,0,12.5,0,2; ...
%                9.2,750.00,287.5,30.0,1,0,0,12.5,0,2; ...
%                9.2,750.00,362.5,30.0,1,0,0,12.5,0,2; ...
%                9.2,937.50,100.0,30.0,1,0,0,12.5,0,2; ...
%                9.2,937.50,175.0,30.0,1,0,0,12.5,0,2; ...
%                9.2,937.50,250.0,30.0,1,0,0,12.5,0,2; ...
%                9.2,937.50,325.0,30.0,1,0,0,12.5,0,2; ...
%                9.2,937.50,400.0,30.0,1,0,0,12.5,0,2; ...
%                9.2,1125.0,137.5,30.0,1,0,0,12.5,0,2; ...
%                9.2,1125.0,212.5,30.0,1,0,0,12.5,0,2; ...
%                9.2,1125.0,287.5,30.0,1,0,0,12.5,0,2; ...
%                9.2,1125.0,362.5,30.0,1,0,0,12.5,0,2 ...
               ];

% coordinates at points along the mooring lines, and the buoyancy pods (plus anything else not a turbine)
% NOTE: avoid using colons, :, in these names (or else!)
probes.names = {'row-01 Mooring-01'; ...
                'row-01 Mooring-02'; ...
                'row-01 Mooring-03'; ...
                'row-01 Mooring-04'; ...
                'row-01 Mooring-05'; ...
                'row-01 Mooring-06'; ...
                'row-01 Mooring-07'; ...
                'row-01 Mooring-08'; ...
                'row-01 Mooring-09'; ...
                'row-01 Mooring-10'; ...
                'row-02 Mooring-01'; ...
                'row-02 Mooring-02'; ...
                'row-02 Mooring-03'; ...
                'row-02 Mooring-04'; ...
                'row-02 Mooring-05'; ...
                'row-02 Mooring-06'; ...
                'row-02 Mooring-07'; ...
                'row-02 Mooring-08'; ...
                'row-02 Mooring-09'; ...
                'row-02 Mooring-10'; ...
                'BuoyancyPod-01'; ...
                'BuoyancyPod-02'; ...
                'BuoyancyPod-03'; ...
                'BuoyancyPod-04'; ...
                'BuoyancyPod-05'; ...
                'BuoyancyPod-06'; ...
                'BuoyancyPod-07'; ...
                'BuoyancyPod-08'; ...
                'BuoyancyPod-09'; ...
                'BuoyancyPod-10'; ...
                'BuoyancyPod-11'; ...
                'BuoyancyPod-12'; ...
                'BuoyancyPod-13' ...
                };            
 probes.xyz =  [187.5       100.0000    44.5; ...
                187.5       133.3333    44.5; ...
                187.5       166.6667    44.5; ...
                187.5       200.0000    44.5; ...
                187.5       233.3333    44.5; ...
                187.5       266.6667    44.5; ...
                187.5       300.0000    44.5; ...
                187.5       333.3333    44.5; ...
                187.5       366.6667    44.5; ...
                187.5       400.0000    44.5; ...
                375     100.0000    44.5; ...
                375     133.3333    44.5; ...
                375     166.6667    44.5; ...
                375     200.0000    44.5; ...
                375     233.3333    44.5; ...
                375     266.6667    44.5; ...
                375     300.0000    44.5; ...
                375     333.3333    44.5; ...
                375     366.6667    44.5; ...
                375     400.0000    44.5; ...
                187.5       100         50; ...
                187.5       175         50; ...
                187.5       250         50; ...
                187.5       325         50; ...
                187.5       400         50; ...
                375     137.5       50; ...
                375     212.5       50; ...
                375     287.5       50; ...
                375     362.5       50; ...
                187.5      50          50; ...
                187.5      450          50; ...
                375         50          50; ...
                375         450         50 ...
                ];
