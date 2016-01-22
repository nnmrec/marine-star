function [OPTIONS] = updateSolverCFD(OPTIONS)
%updateSolverCFD updates some of the convergence criteria for the CFD solver
%   Detailed explanation goes here

OPTIONS.max_iter         = 2   * OPTIONS.max_iter;         
OPTIONS.limit_continuity = 0.1 * OPTIONS.limit_continuity;



%need to run a STAR macro here instead



% macroFile = [pwd filesep 'macros' filesep 'createMeshBackground.java'];
% 
% %% replace max iterations
% c     = textread(macroFile,'%s','delimiter','\n');
% lines = find(~cellfun(@isempty,strfind(c,'limit_continuity')));
% 
% fid = fopen(macroFile, 'r+');
% for n = 1:lines(1)-1
%    fgetl(fid)
% end
% fprintf(fid, ['  static final int    iter_max                = ' num2str(OPTIONS.max_iter) ';     // stop after this many iterations' '\n']);
% fclose(fid);
% 
% 
% %% replace continuity criteria
% c     = textread(macroFile,'%s','delimiter','\n');
% lines = find(~cellfun(@isempty,strfind(c,'limit_continuity')));
% 
% fid = fopen(macroFile, 'r+');
% for n = 1:lines(1)-1
%    fgetl(fid)
% end
% fprintf(fid, ['  static final double limit_continuity        = ' num2str(OPTIONS.limit_continuity) ';   // stop when residual of continuity reaches this value' '\n']);
% fclose(fid);

    
end

