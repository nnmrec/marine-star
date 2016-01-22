function runSTARCCM(OPTIONS,macro)
%runSTARCCM runs the STAR-CCM+ model
%   Detailed explanation goes here

% run STAR-CCM+
% if runOnHPC
%    % should this section also modify the PBS script, based on mesh size, etc.  ?
%     system('./submit-job-Hyak.sh');
% else
    system(['cp --no-clobber ' OPTIONS.starSimFile ' runs.' OPTIONS.starSimFile]);
%     system(['rm log.' OPTIONS.starSimFile]);
    system(['starccm+ -batch macros/' macro ' -np ' num2str(OPTIONS.nCPUs) ' -licpath 1999@flex.cd-adapco.com -power -podkey $myPODkey -batch-report runs.' OPTIONS.starSimFile ' 2>&1 | tee log.' OPTIONS.starSimFile]);
% end

end

