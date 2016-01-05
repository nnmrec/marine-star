#!/bin/bash

## --------------------------------------------------------
## NOTE: to submit jobs to Hyak use
##       qsub <script.sh>
##
## #PBS is a directive requesting job scheduling resources
## and ALL PBS directives must be at the top of the script, 
## standard bash commands can follow afterwards. 
## --------------------------------------------------------

## --------------------------------------------------------
## RENAME for your job
## --------------------------------------------------------
#PBS -N Mezzanine


## --------------------------------------------------------
## GROUP to run under, or run under backfill
## --------------------------------------------------------
#PBS -W group_list=hyak-motley
## PBS -W group_list=hyak-stf
## PBS -q bf


## --------------------------------------------------------
## NUMBER nodes, CPUs per node, and MEMORY
## --------------------------------------------------------
#PBS -l nodes=1:ppn=16,mem=55gb,feature=intel
## PBS -l nodes=2:ppn=16,mem=60gb,feature=intel
## PBS -l nodes=3:ppn=16,mem=150gb,feature=intel
## PBS -l nodes=4:ppn=16,mem=60gb,feature=intel
## PBS -l nodes=8:ppn=16,mem=60gb,feature=intel
## PBS -l nodes=16:ppn=16,mem=60gb,feature=intel


## --------------------------------------------------------
## WALLTIME (defaults to 1 hour, always specify for longer jobs)
## --------------------------------------------------------
#PBS -l walltime=01:59:00


## --------------------------------------------------------
## LOG the (stderr and stdout) job output in the directory
## --------------------------------------------------------
## PBS -j oe -o /gscratch/motley/dsale/job_output/logs
#PBS -j oe -o /gscratch/stf/dsale/job_output/logs


## --------------------------------------------------------
## EMAIL to send when job is aborted, begins, and terminates
## --------------------------------------------------------
## PBS -m abe -M dsale@uw.edu
#PBS -m abe -M sale.danny@gmail.com


## --------------------------------------------------------
## END of PBS commmands ... only BASH from here and below
## --------------------------------------------------------


## --------------------------------------------------------
## LOAD the appropriate environment modules and variables
## --------------------------------------------------------
# module load contrib/starccm_10.06.009-R8
module load contrib/starccm_10.06.009-R8


## --------------------------------------------------------
## Debugging information (include jobs logs in any help requests)
## --------------------------------------------------------
## Total Number of nodes and processors (cores) to be used by the job
echo "** JOB DEBUGGING INFORMATION  *************************"
HYAK_NNODES=$(uniq $PBS_NODEFILE | wc -l )
HYAK_NPE=$(wc -l < $PBS_NODEFILE)
echo "This job will run on $HYAK_NNODES nodes with $HYAK_NPE total CPU-cores"
echo ""
echo "Node:CPUs Used"
uniq -c $PBS_NODEFILE | awk '{print $2 ":" $1}'
echo ""
echo "ENVIRONMENT VARIABLES"
set
echo ""
echo "** END DEBUGGING INFORMATION  *************************"


## -------------------------------------------------------- 
## Specify the applications to run here
## -------------------------------------------------------- 

starSimFile="Mezzanine_v0.sim"

starMacros="macros/main.java"
myPODkey="r7L8XXSBzQYnzS/zQEZ6Jw"


## CHANGE directory to where job was submitted (careful, PBS defaults to user home directory)
cd $PBS_O_WORKDIR


## KEEP copy of the initial cleared solution (small file size), rename file used for restart after checkpointing (this file gets big) 
cp --no-clobber $starSimFile runs.$starSimFile
rm log.$starSimFile
# rm *.sim~

## RUN my simulation file in batch mode
starccm+ -batch $starMacros -np ${PBS_NP} -machinefile ${PBS_NODEFILE} -licpath 1999@login3.hyak.local -power -podkey $myPODkey -batch-report runs.$starSimFile 2>&1 | tee log.$starSimFile
