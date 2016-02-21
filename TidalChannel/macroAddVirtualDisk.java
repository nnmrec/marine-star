// STAR-CCM+ macro: macroAddVirtualDisk.java
// Written by STAR-CCM+ 10.04.009
package macro;

import java.util.*;

import star.vdm.*;
import star.common.*;
import star.base.neo.*;
import star.base.report.*;
import star.trimmer.*;
import star.meshing.*;
import star.vis.*;


public class macroAddVirtualDisk extends StarMacro {
	// This creates Virtual Disks at specified coordinates and specifications



	///////////////////////////////////////////////////////////////////////////////
	// USER INPUTS
  	// static final int nVirtualDisks = 20;
  	static final int nVirtualDisks = 98;

	// ///////////////////////////////////////////////////////////////////////////////
	// turbine specifications
	static final double hub_radius    = 1.0;
	static final double rotor_radius  = 10.0;
	static final double rotor_thick   = 2.0;
	static final double rotor_spacing = 28.0;
	static final double rotor_height  = 30.0;
	static final double rotor_rpm     = 11.5;
	
	// static final double nx            = 1.0;
	// static final double ny            = -1.0;
	// static final double nz            = 0.0;
	static final double nx            = 1.0;
	static final double ny            = 0.0;
	static final double nz            = 0.0;



///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

	// turbine names
	// String[] name_VirtualDisks = new String[]
	// {
	// 	"turbine  1: ccw",
	// 	"turbine  2: ccw",
	// 	"turbine  3: ccw",
	// 	"turbine  4: ccw",
	// 	"turbine  5: ccw",
	// 	"turbine  6: ccw",
	// 	"turbine  7: ccw",
	// 	"turbine  8: ccw",
	// 	"turbine  9: ccw",
	// 	"turbine 10: ccw",
	// 	"turbine  1: cw",
	// 	"turbine  2: cw",
	// 	"turbine  3: cw",
	// 	"turbine  4: cw",
	// 	"turbine  5: cw",
	// 	"turbine  6: cw",
	// 	"turbine  7: cw",
	// 	"turbine  8: cw",
	// 	"turbine  9: cw",
	// 	"turbine 10: cw"
	// };
	// String[] name_VirtualDiskMarker = new String[]
	// {
	// 	"VirtualDiskMarker1",
	// 	"VirtualDiskMarker2",
	// 	"VirtualDiskMarker3",
	// 	"VirtualDiskMarker4",
	// 	"VirtualDiskMarker5",
	// 	"VirtualDiskMarker6",
	// 	"VirtualDiskMarker7",
	// 	"VirtualDiskMarker8",
	// 	"VirtualDiskMarker9",
	// 	"VirtualDiskMarker10",
	// 	"VirtualDiskMarker11",
	// 	"VirtualDiskMarker12",
	// 	"VirtualDiskMarker13",
	// 	"VirtualDiskMarker14",
	// 	"VirtualDiskMarker15",
	// 	"VirtualDiskMarker16",
	// 	"VirtualDiskMarker17",
	// 	"VirtualDiskMarker18",
	// 	"VirtualDiskMarker19",
	// 	"VirtualDiskMarker20"
	// };
	// String[] name_VirtualDiskInflowPlaneMarker = new String[]
	// {
	// 	"VirtualDiskInflowPlaneMarker1",
	// 	"VirtualDiskInflowPlaneMarker2",
	// 	"VirtualDiskInflowPlaneMarker3",
	// 	"VirtualDiskInflowPlaneMarker4",
	// 	"VirtualDiskInflowPlaneMarker5",
	// 	"VirtualDiskInflowPlaneMarker6",
	// 	"VirtualDiskInflowPlaneMarker7",
	// 	"VirtualDiskInflowPlaneMarker8",
	// 	"VirtualDiskInflowPlaneMarker9",
	// 	"VirtualDiskInflowPlaneMarker10",
	// 	"VirtualDiskInflowPlaneMarker11",
	// 	"VirtualDiskInflowPlaneMarker12",
	// 	"VirtualDiskInflowPlaneMarker13",
	// 	"VirtualDiskInflowPlaneMarker14",
	// 	"VirtualDiskInflowPlaneMarker15",
	// 	"VirtualDiskInflowPlaneMarker16",
	// 	"VirtualDiskInflowPlaneMarker17",
	// 	"VirtualDiskInflowPlaneMarker18",
	// 	"VirtualDiskInflowPlaneMarker19",
	// 	"VirtualDiskInflowPlaneMarker20"
	// };


	// turbine coordinates - SINGLE FENCE 1/4 Channel
	// double[][] coords_VirtualDisks = new double[][]
	// {
	// 	{10000,660,-20},
	// 	{10000,725.56,-32.494},
	// 	{10000,791.11,-44.811},
	// 	{10000,856.67,-58.912},
	// 	{10000,922.22,-66.235},
	// 	{10000,987.78,-69.76},
	// 	{10000,1053.3,-70},
	// 	{10000,1118.9,-70},
	// 	{10000,1184.4,-70},
	// 	{10000,1250,-70},
	// 	{10000,688,-20},
	// 	{10000,753.56,-32.494},
	// 	{10000,819.11,-44.811},
	// 	{10000,884.67,-58.912},
	// 	{10000,950.22,-66.235},
	// 	{10000,1015.8,-69.76},
	// 	{10000,1081.3,-70},
	// 	{10000,1146.9,-70},
	// 	{10000,1212.4,-70},
	// 	{10000,1278,-70}
	// };
  	
  	// turbine coordinates - SINGLE FENCE 1/2 Channel
	// double[][] coords_VirtualDisks = new double[][]
	// {
	// 	{10000,660,-20},
	// 	{10000,864.44,-58.912},
	// 	{10000,1068.9,-70},
	// 	{10000,1273.3,-70},
	// 	{10000,1477.8,-70},
	// 	{10000,1682.2,-70},
	// 	{10000,1886.7,-70},
	// 	{10000,2091.1,-70},
	// 	{10000,2295.6,-70},
	// 	{10000,2500,-70},
	// 	{10000,688,-20},
	// 	{10000,892.44,-58.912},
	// 	{10000,1096.9,-70},
	// 	{10000,1301.3,-70},
	// 	{10000,1505.8,-70},
	// 	{10000,1710.2,-70},
	// 	{10000,1914.7,-70},
	// 	{10000,2119.1,-70},
	// 	{10000,2323.6,-70},
	// 	{10000,2528,-70}
	// };

  	// turbine coordinates - Admiralty staggered layout
	// double[][] coords_VirtualDisks = new double[][]
	// {
	// 	{570,720,-26.325},
	// 	{610,830,-24.586},
	// 	{660,930,-23.75},
	// 	{790,800,-26.426},
	// 	{720,690,-28.598},
	// 	{850,620,-32.988},
	// 	{880,720,-25.189},
	// 	{920,800,-32.331},
	// 	{1060,670,-31.419},
	// 	{1000,590,-26.734},
	// 	{589.8,739.8,-26.325},
	// 	{629.8,849.8,-24.586},
	// 	{679.8,949.8,-23.75},
	// 	{809.8,819.8,-26.426},
	// 	{739.8,709.8,-28.598},
	// 	{869.8,639.8,-32.988},
	// 	{899.8,739.8,-25.189},
	// 	{939.8,819.8,-32.331},
	// 	{1079.8,689.8,-31.419},
	// 	{1019.8,609.8,-26.734}
	// };



///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

// turbine names
	String[] name_VirtualDisks = new String[]
	{
		"turbine  1: ccw",
		"turbine  2: ccw",
		"turbine  3: ccw",
		"turbine  4: ccw",
		"turbine  5: ccw",
		"turbine  6: ccw",
		"turbine  7: ccw",
		"turbine  8: ccw",
		"turbine  9: ccw",
		"turbine 10: ccw",
		"turbine 11: ccw",
		"turbine 12: ccw",
		"turbine 13: ccw",
		"turbine 14: ccw",
		"turbine 15: ccw",
		"turbine 16: ccw",
		"turbine 17: ccw",
		"turbine 18: ccw",
		"turbine 19: ccw",
		"turbine 20: ccw",
		"turbine 21: ccw",
		"turbine 22: ccw",
		"turbine 23: ccw",
		"turbine 24: ccw",
		"turbine 25: ccw",
		"turbine 26: ccw",
		"turbine 27: ccw",
		"turbine 28: ccw",
		"turbine 29: ccw",
		"turbine 30: ccw",
		"turbine 31: ccw",
		"turbine 32: ccw",
		"turbine 33: ccw",
		"turbine 34: ccw",
		"turbine 35: ccw",
		"turbine 36: ccw",
		"turbine 37: ccw",
		"turbine 38: ccw",
		"turbine 39: ccw",
		"turbine 40: ccw",
		"turbine 41: ccw",
		"turbine 42: ccw",
		"turbine 43: ccw",
		"turbine 44: ccw",
		"turbine 45: ccw",
		"turbine 46: ccw",
		"turbine 47: ccw",
		"turbine 48: ccw",
		"turbine 49: ccw",
		"turbine  1: cw",
		"turbine  2: cw",
		"turbine  3: cw",
		"turbine  4: cw",
		"turbine  5: cw",
		"turbine  6: cw",
		"turbine  7: cw",
		"turbine  8: cw",
		"turbine  9: cw",
		"turbine 10: cw",
		"turbine 11: cw",
		"turbine 12: cw",
		"turbine 13: cw",
		"turbine 14: cw",
		"turbine 15: cw",
		"turbine 16: cw",
		"turbine 17: cw",
		"turbine 18: cw",
		"turbine 19: cw",
		"turbine 20: cw",
		"turbine 21: cw",
		"turbine 22: cw",
		"turbine 23: cw",
		"turbine 24: cw",
		"turbine 25: cw",
		"turbine 26: cw",
		"turbine 27: cw",
		"turbine 28: cw",
		"turbine 29: cw",
		"turbine 30: cw",
		"turbine 31: cw",
		"turbine 32: cw",
		"turbine 33: cw",
		"turbine 34: cw",
		"turbine 35: cw",
		"turbine 36: cw",
		"turbine 37: cw",
		"turbine 38: cw",
		"turbine 39: cw",
		"turbine 40: cw",
		"turbine 41: cw",
		"turbine 42: cw",
		"turbine 43: cw",
		"turbine 44: cw",
		"turbine 45: cw",
		"turbine 46: cw",
		"turbine 47: cw",
		"turbine 48: cw",
		"turbine 49: cw"
	};
	String[] name_VirtualDiskMarker = new String[]
	{
		"VirtualDiskMarker1",
		"VirtualDiskMarker2",
		"VirtualDiskMarker3",
		"VirtualDiskMarker4",
		"VirtualDiskMarker5",
		"VirtualDiskMarker6",
		"VirtualDiskMarker7",
		"VirtualDiskMarker8",
		"VirtualDiskMarker9",
		"VirtualDiskMarker10",
		"VirtualDiskMarker11",
		"VirtualDiskMarker12",
		"VirtualDiskMarker13",
		"VirtualDiskMarker14",
		"VirtualDiskMarker15",
		"VirtualDiskMarker16",
		"VirtualDiskMarker17",
		"VirtualDiskMarker18",
		"VirtualDiskMarker19",
		"VirtualDiskMarker20",
		"VirtualDiskMarker21",
		"VirtualDiskMarker22",
		"VirtualDiskMarker23",
		"VirtualDiskMarker24",
		"VirtualDiskMarker25",
		"VirtualDiskMarker26",
		"VirtualDiskMarker27",
		"VirtualDiskMarker28",
		"VirtualDiskMarker29",
		"VirtualDiskMarker30",
		"VirtualDiskMarker31",
		"VirtualDiskMarker32",
		"VirtualDiskMarker33",
		"VirtualDiskMarker34",
		"VirtualDiskMarker35",
		"VirtualDiskMarker36",
		"VirtualDiskMarker37",
		"VirtualDiskMarker38",
		"VirtualDiskMarker39",
		"VirtualDiskMarker40",
		"VirtualDiskMarker41",
		"VirtualDiskMarker42",
		"VirtualDiskMarker43",
		"VirtualDiskMarker44",
		"VirtualDiskMarker45",
		"VirtualDiskMarker46",
		"VirtualDiskMarker47",
		"VirtualDiskMarker48",
		"VirtualDiskMarker49",
		"VirtualDiskMarker50",
		"VirtualDiskMarker51",
		"VirtualDiskMarker52",
		"VirtualDiskMarker53",
		"VirtualDiskMarker54",
		"VirtualDiskMarker55",
		"VirtualDiskMarker56",
		"VirtualDiskMarker57",
		"VirtualDiskMarker58",
		"VirtualDiskMarker59",
		"VirtualDiskMarker60",
		"VirtualDiskMarker61",
		"VirtualDiskMarker62",
		"VirtualDiskMarker63",
		"VirtualDiskMarker64",
		"VirtualDiskMarker65",
		"VirtualDiskMarker66",
		"VirtualDiskMarker67",
		"VirtualDiskMarker68",
		"VirtualDiskMarker69",
		"VirtualDiskMarker70",
		"VirtualDiskMarker71",
		"VirtualDiskMarker72",
		"VirtualDiskMarker73",
		"VirtualDiskMarker74",
		"VirtualDiskMarker75",
		"VirtualDiskMarker76",
		"VirtualDiskMarker77",
		"VirtualDiskMarker78",
		"VirtualDiskMarker79",
		"VirtualDiskMarker80",
		"VirtualDiskMarker81",
		"VirtualDiskMarker82",
		"VirtualDiskMarker83",
		"VirtualDiskMarker84",
		"VirtualDiskMarker85",
		"VirtualDiskMarker86",
		"VirtualDiskMarker87",
		"VirtualDiskMarker88",
		"VirtualDiskMarker89",
		"VirtualDiskMarker90",
		"VirtualDiskMarker91",
		"VirtualDiskMarker92",
		"VirtualDiskMarker93",
		"VirtualDiskMarker94",
		"VirtualDiskMarker95",
		"VirtualDiskMarker96",
		"VirtualDiskMarker97",
		"VirtualDiskMarker98"
	};
	String[] name_VirtualDiskInflowPlaneMarker = new String[]
	{
		"VirtualDiskInflowPlaneMarker1",
		"VirtualDiskInflowPlaneMarker2",
		"VirtualDiskInflowPlaneMarker3",
		"VirtualDiskInflowPlaneMarker4",
		"VirtualDiskInflowPlaneMarker5",
		"VirtualDiskInflowPlaneMarker6",
		"VirtualDiskInflowPlaneMarker7",
		"VirtualDiskInflowPlaneMarker8",
		"VirtualDiskInflowPlaneMarker9",
		"VirtualDiskInflowPlaneMarker10",
		"VirtualDiskInflowPlaneMarker11",
		"VirtualDiskInflowPlaneMarker12",
		"VirtualDiskInflowPlaneMarker13",
		"VirtualDiskInflowPlaneMarker14",
		"VirtualDiskInflowPlaneMarker15",
		"VirtualDiskInflowPlaneMarker16",
		"VirtualDiskInflowPlaneMarker17",
		"VirtualDiskInflowPlaneMarker18",
		"VirtualDiskInflowPlaneMarker19",
		"VirtualDiskInflowPlaneMarker20",
		"VirtualDiskInflowPlaneMarker21",
		"VirtualDiskInflowPlaneMarker22",
		"VirtualDiskInflowPlaneMarker23",
		"VirtualDiskInflowPlaneMarker24",
		"VirtualDiskInflowPlaneMarker25",
		"VirtualDiskInflowPlaneMarker26",
		"VirtualDiskInflowPlaneMarker27",
		"VirtualDiskInflowPlaneMarker28",
		"VirtualDiskInflowPlaneMarker29",
		"VirtualDiskInflowPlaneMarker30",
		"VirtualDiskInflowPlaneMarker31",
		"VirtualDiskInflowPlaneMarker32",
		"VirtualDiskInflowPlaneMarker33",
		"VirtualDiskInflowPlaneMarker34",
		"VirtualDiskInflowPlaneMarker35",
		"VirtualDiskInflowPlaneMarker36",
		"VirtualDiskInflowPlaneMarker37",
		"VirtualDiskInflowPlaneMarker38",
		"VirtualDiskInflowPlaneMarker39",
		"VirtualDiskInflowPlaneMarker40",
		"VirtualDiskInflowPlaneMarker41",
		"VirtualDiskInflowPlaneMarker42",
		"VirtualDiskInflowPlaneMarker43",
		"VirtualDiskInflowPlaneMarker44",
		"VirtualDiskInflowPlaneMarker45",
		"VirtualDiskInflowPlaneMarker46",
		"VirtualDiskInflowPlaneMarker47",
		"VirtualDiskInflowPlaneMarker48",
		"VirtualDiskInflowPlaneMarker49",
		"VirtualDiskInflowPlaneMarker50",
		"VirtualDiskInflowPlaneMarker51",
		"VirtualDiskInflowPlaneMarker52",
		"VirtualDiskInflowPlaneMarker53",
		"VirtualDiskInflowPlaneMarker54",
		"VirtualDiskInflowPlaneMarker55",
		"VirtualDiskInflowPlaneMarker56",
		"VirtualDiskInflowPlaneMarker57",
		"VirtualDiskInflowPlaneMarker58",
		"VirtualDiskInflowPlaneMarker59",
		"VirtualDiskInflowPlaneMarker60",
		"VirtualDiskInflowPlaneMarker61",
		"VirtualDiskInflowPlaneMarker62",
		"VirtualDiskInflowPlaneMarker63",
		"VirtualDiskInflowPlaneMarker64",
		"VirtualDiskInflowPlaneMarker65",
		"VirtualDiskInflowPlaneMarker66",
		"VirtualDiskInflowPlaneMarker67",
		"VirtualDiskInflowPlaneMarker68",
		"VirtualDiskInflowPlaneMarker69",
		"VirtualDiskInflowPlaneMarker70",
		"VirtualDiskInflowPlaneMarker71",
		"VirtualDiskInflowPlaneMarker72",
		"VirtualDiskInflowPlaneMarker73",
		"VirtualDiskInflowPlaneMarker74",
		"VirtualDiskInflowPlaneMarker75",
		"VirtualDiskInflowPlaneMarker76",
		"VirtualDiskInflowPlaneMarker77",
		"VirtualDiskInflowPlaneMarker78",
		"VirtualDiskInflowPlaneMarker79",
		"VirtualDiskInflowPlaneMarker80",
		"VirtualDiskInflowPlaneMarker81",
		"VirtualDiskInflowPlaneMarker82",
		"VirtualDiskInflowPlaneMarker83",
		"VirtualDiskInflowPlaneMarker84",
		"VirtualDiskInflowPlaneMarker85",
		"VirtualDiskInflowPlaneMarker86",
		"VirtualDiskInflowPlaneMarker87",
		"VirtualDiskInflowPlaneMarker88",
		"VirtualDiskInflowPlaneMarker89",
		"VirtualDiskInflowPlaneMarker90",
		"VirtualDiskInflowPlaneMarker91",
		"VirtualDiskInflowPlaneMarker92",
		"VirtualDiskInflowPlaneMarker93",
		"VirtualDiskInflowPlaneMarker94",
		"VirtualDiskInflowPlaneMarker95",
		"VirtualDiskInflowPlaneMarker96",
		"VirtualDiskInflowPlaneMarker97",
		"VirtualDiskInflowPlaneMarker98"
	};
  	// turbine coordinates - channel with sill - 49 turbines, 7 x 7 staggered grid
	// double[][] coords_VirtualDisks = new double[][]
	// {
	// 	{9400,660,-20},
	// 	{9400,780,-24.245},
	// 	{9400,900,-24.245},
	// 	{9400,1020,-24.245},
	// 	{9400,1140,-24.245},
	// 	{9400,1260,-24.245},
	// 	{9400,1380,-24.245},
	// 	{9600,720,-21.964},
	// 	{9600,840,-21.964},
	// 	{9600,960,-21.964},
	// 	{9600,1080,-21.964},
	// 	{9600,1200,-21.964},
	// 	{9600,1320,-21.964},
	// 	{9600,1440,-21.964},
	// 	{9800,660,-20},
	// 	{9800,780,-20.523},
	// 	{9800,900,-20.523},
	// 	{9800,1020,-20.523},
	// 	{9800,1140,-20.523},
	// 	{9800,1260,-20.523},
	// 	{9800,1380,-20.523},
	// 	{10000,720,-20.001},
	// 	{10000,840,-20.001},
	// 	{10000,960,-20.001},
	// 	{10000,1080,-20.001},
	// 	{10000,1200,-20.001},
	// 	{10000,1320,-20.001},
	// 	{10000,1440,-20.001},
	// 	{10200,660,-20},
	// 	{10200,780,-20.523},
	// 	{10200,900,-20.523},
	// 	{10200,1020,-20.523},
	// 	{10200,1140,-20.523},
	// 	{10200,1260,-20.523},
	// 	{10200,1380,-20.523},
	// 	{10400,720,-21.964},
	// 	{10400,840,-21.964},
	// 	{10400,960,-21.964},
	// 	{10400,1080,-21.964},
	// 	{10400,1200,-21.964},
	// 	{10400,1320,-21.964},
	// 	{10400,1440,-21.964},
	// 	{10600,660,-20},
	// 	{10600,780,-24.245},
	// 	{10600,900,-24.245},
	// 	{10600,1020,-24.245},
	// 	{10600,1140,-24.245},
	// 	{10600,1260,-24.245},
	// 	{10600,1380,-24.245},
	// 	{9400,688,-20},
	// 	{9400,808,-24.245},
	// 	{9400,928,-24.245},
	// 	{9400,1048,-24.245},
	// 	{9400,1168,-24.245},
	// 	{9400,1288,-24.245},
	// 	{9400,1408,-24.245},
	// 	{9600,748,-21.964},
	// 	{9600,868,-21.964},
	// 	{9600,988,-21.964},
	// 	{9600,1108,-21.964},
	// 	{9600,1228,-21.964},
	// 	{9600,1348,-21.964},
	// 	{9600,1468,-21.964},
	// 	{9800,688,-20},
	// 	{9800,808,-20.523},
	// 	{9800,928,-20.523},
	// 	{9800,1048,-20.523},
	// 	{9800,1168,-20.523},
	// 	{9800,1288,-20.523},
	// 	{9800,1408,-20.523},
	// 	{10000,748,-20.001},
	// 	{10000,868,-20.001},
	// 	{10000,988,-20.001},
	// 	{10000,1108,-20.001},
	// 	{10000,1228,-20.001},
	// 	{10000,1348,-20.001},
	// 	{10000,1468,-20.001},
	// 	{10200,688,-20},
	// 	{10200,808,-20.523},
	// 	{10200,928,-20.523},
	// 	{10200,1048,-20.523},
	// 	{10200,1168,-20.523},
	// 	{10200,1288,-20.523},
	// 	{10200,1408,-20.523},
	// 	{10400,748,-21.964},
	// 	{10400,868,-21.964},
	// 	{10400,988,-21.964},
	// 	{10400,1108,-21.964},
	// 	{10400,1228,-21.964},
	// 	{10400,1348,-21.964},
	// 	{10400,1468,-21.964},
	// 	{10600,688,-20},
	// 	{10600,808,-24.245},
	// 	{10600,928,-24.245},
	// 	{10600,1048,-24.245},
	// 	{10600,1168,-24.245},
	// 	{10600,1288,-24.245},
	// 	{10600,1408,-24.245}
	// };
	// turbine coordinates - channel with sill - 49 turbines, 7 x 7 staggered grid
	double[][] coords_VirtualDisks = new double[][]
	{
		{9000,660,-20},
		{9300,660,-20},
		{9500,660,-20},
		{9600,660,-20},
		{9900,660,-20},
		{10000,660,-20},
		{10100,660,-20},
		{10400,660,-20},
		{10500,660,-20},
		{10700,660,-20},
		{11000,660,-20},
		{9000,1660,-31.175},
		{9300,1660,-25.663},
		{9500,1660,-23.007},
		{9600,1660,-21.964},
		{9900,1660,-20.144},
		{10000,1660,-20.001},
		{10100,1660,-20.144},
		{10400,1660,-21.964},
		{10500,1660,-23.007},
		{10700,1660,-25.663},
		{11000,1660,-31.175},
		{9720,760,-20.993},
		{9760,860,-20.74},
		{9783.3,960,-20.627},
		{9796,1060,-20.523},
		{9800,1160,-20.523},
		{9796,1260,-20.523},
		{9783.3,1360,-20.627},
		{9760,1460,-20.74},
		{9720,1560,-20.993},
		{10000,760,-20.001},
		{10000,860,-20.001},
		{10000,960,-20.001},
		{10000,1060,-20.001},
		{10000,1160,-20.001},
		{10000,1260,-20.001},
		{10000,1360,-20.001},
		{10000,1460,-20.001},
		{10000,1560,-20.001},
		{10280,760,-20.993},
		{10240,860,-20.74},
		{10217,960,-20.627},
		{10204,1060,-20.523},
		{10200,1160,-20.523},
		{10204,1260,-20.523},
		{10217,1360,-20.627},
		{10240,1460,-20.74},
		{10280,1560,-20.993},
		{9000,688,-20},
		{9300,688,-20},
		{9500,688,-20},
		{9600,688,-20},
		{9900,688,-20},
		{10000,688,-20},
		{10100,688,-20},
		{10400,688,-20},
		{10500,688,-20},
		{10700,688,-20},
		{11000,688,-20},
		{9000,1688,-31.175},
		{9300,1688,-25.663},
		{9500,1688,-23.007},
		{9600,1688,-21.964},
		{9900,1688,-20.144},
		{10000,1688,-20.001},
		{10100,1688,-20.144},
		{10400,1688,-21.964},
		{10500,1688,-23.007},
		{10700,1688,-25.663},
		{11000,1688,-31.175},
		{9720,788,-20.993},
		{9760,888,-20.74},
		{9783.3,988,-20.627},
		{9796,1088,-20.523},
		{9800,1188,-20.523},
		{9796,1288,-20.523},
		{9783.3,1388,-20.627},
		{9760,1488,-20.74},
		{9720,1588,-20.993},
		{10000,788,-20.001},
		{10000,888,-20.001},
		{10000,988,-20.001},
		{10000,1088,-20.001},
		{10000,1188,-20.001},
		{10000,1288,-20.001},
		{10000,1388,-20.001},
		{10000,1488,-20.001},
		{10000,1588,-20.001},
		{10280,788,-20.993},
		{10240,888,-20.74},
		{10217,988,-20.627},
		{10204,1088,-20.523},
		{10200,1188,-20.523},
		{10204,1288,-20.523},
		{10217,1388,-20.627},
		{10240,1488,-20.74},
		{10280,1588,-20.993}
	};







///////////////////////////////////////////////////////////////////////////////

	public void execute() {
		execute0();
	}

///////////////////////////////////////////////////////////////////////////////

	private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();


    PhysicsContinuum physicsContinuum_0 = 
      ((PhysicsContinuum) simulation_0.getContinuumManager().getContinuum("Physics 1"));

	FileTable fileTable_0 = 
	  (FileTable) simulation_0.getTableManager().createFromFile(resolvePath("inputs/virtual-blade-1DM_Speed_Power_Ct.csv"));

	Region region_0 = 
	  simulation_0.getRegionManager().getRegion("channel");

	// create a new virtual disk
	for (int i = 0; i < nVirtualDisks; i++) {
		
	    VirtualDiskModel virtualDiskModel_0 = 
	      physicsContinuum_0.getModelManager().getModel(VirtualDiskModel.class);

	    VirtualDisk virtualDisk_0 = 
	      virtualDiskModel_0.getVirtualDiskManager().createDisk(name_VirtualDisks[i]);

      	virtualDisk_0.setDisplaySourceTerm(true);

	    virtualDisk_0.setActiveMethod(OneDMomentumMethod.class);

	    virtualDisk_0.getComponentsManager().get(PowerCurve.class).setActiveMethod(PowerCurveTableMethod.class);

	    PowerCurveTableMethod powerCurveTableMethod_0 = 
	      ((PowerCurveTableMethod) virtualDisk_0.getComponentsManager().get(PowerCurve.class).getActiveMethod());



	    powerCurveTableMethod_0.setTable(fileTable_0);

	    powerCurveTableMethod_0.setWindSpeed("Wind-speed_m/s");

	    powerCurveTableMethod_0.setPower("Power_watts");

	    powerCurveTableMethod_0.setThrustCoeff("Thrust-Ct");

	    SimpleDiskGeometry simpleDiskGeometry_0 = 
	      virtualDisk_0.getComponentsManager().get(SimpleDiskGeometry.class);

	    simpleDiskGeometry_0.getDiskOuterRadius().setValue(rotor_radius);

	    simpleDiskGeometry_0.getDiskInnerRadius().setValue(hub_radius);

	    simpleDiskGeometry_0.getDiskThickness().setValue(rotor_thick);

	    Coordinate coordinate_disk = 
	      simpleDiskGeometry_0.getDiskOrigin();

	    Units units_m = 
	      ((Units) simulation_0.getUnitsManager().getObject("m"));

	    coordinate_disk.setCoordinate(units_m, units_m, units_m, new DoubleVector(new double[] {coords_VirtualDisks[i][0], coords_VirtualDisks[i][1], coords_VirtualDisks[i][2]}));

	    ((NormalAndCoordinateSystem) simpleDiskGeometry_0.getOrientationSpecification()).getDiskNormal().setComponents(nx, ny, nz);

	    PropellerInflowVelocityPlane propellerInflowVelocityPlane_2 = 
	      virtualDisk_0.getComponentsManager().get(PropellerInflowVelocityPlane.class);

	    propellerInflowVelocityPlane_2.getRadius().setValue(rotor_radius);

	    propellerInflowVelocityPlane_2.getOffset().setValue(-1*4*rotor_radius);

	    VdmRotationRateInputValue vdmRotationRateInputValue_2 = 
	      virtualDisk_0.getComponentsManager().get(VdmRotationRateInputValue.class);

	    Units units_rpm = 
	      ((Units) simulation_0.getUnitsManager().getObject("rpm"));

	    vdmRotationRateInputValue_2.getRotationRate().setUnits(units_rpm);

	    vdmRotationRateInputValue_2.getRotationRate().setValue(rotor_rpm);
















	    ///////////////////////////////////////////////////////////////////////////////
		// create reports and monitors
		// Torque
		VirtualDiskMomentReport virtualDiskMomentReport_0 = 
      	  simulation_0.getReportManager().createReport(VirtualDiskMomentReport.class);

      	virtualDiskMomentReport_0.setVirtualDisk(virtualDisk_0);

		virtualDiskMomentReport_0.setPresentationName("Torque " + name_VirtualDisks[i]);

	    VirtualDiskMomentReport virtualDiskMomentReport_1 = 
	      ((VirtualDiskMomentReport) simulation_0.getReportManager().getReport("Torque " + name_VirtualDisks[i]));

	    ReportMonitor reportMonitor_5 = 
	      virtualDiskMomentReport_1.createMonitor();

	    // Thrust
		VirtualDiskForceReport virtualDiskForceReport_0 = 
          simulation_0.getReportManager().createReport(VirtualDiskForceReport.class);
		  
		virtualDiskForceReport_0.setVirtualDisk(virtualDisk_0);

		virtualDiskForceReport_0.setPresentationName("Thrust " + name_VirtualDisks[i]);

	    VirtualDiskForceReport virtualDiskForceReport_1 = 
	      ((VirtualDiskForceReport) simulation_0.getReportManager().getReport("Thrust " + name_VirtualDisks[i]));

	    ReportMonitor reportMonitor_6 = 
	      virtualDiskForceReport_1.createMonitor();



    
















	    ///////////////////////////////////////////////////////////////////////////////
		// CYLINDER create the Shape Part
	    // Units units_2 = 
	    //   simulation_0.getUnitsManager().getPreferredUnits(new IntVector(new int[] {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));

	    MeshPartFactory meshPartFactory_0 = 
	      simulation_0.get(MeshPartFactory.class);

	    SimpleCylinderPart simpleCylinderPart_0 = 
	      meshPartFactory_0.createNewCylinderPart(simulation_0.get(SimulationPartManager.class));
	      simpleCylinderPart_0.setPresentationName("refine cylinder: " + name_VirtualDisks[i]);

	    simpleCylinderPart_0.setDoNotRetessellate(true);

	    // set the coordinate system
	    LabCoordinateSystem labCoordinateSystem_0 = 
	      simulation_0.getCoordinateSystemManager().getLabCoordinateSystem();

	    CartesianCoordinateSystem cartesianCoordinateSystem_1 = 
	      // ((CartesianCoordinateSystem) labCoordinateSystem_0.getLocalCoordinateSystemManager().getObject("turbine  1: cw-CSys 1"));
	      ((CartesianCoordinateSystem) labCoordinateSystem_0.getLocalCoordinateSystemManager().getObject(name_VirtualDisks[i] + "-CSys 1"));
	      
	    simpleCylinderPart_0.setCoordinateSystem(cartesianCoordinateSystem_1);

	    // start coordinate
	    Coordinate coordinate_5 = 
	      simpleCylinderPart_0.getStartCoordinate();
	    coordinate_5.setCoordinateSystem(cartesianCoordinateSystem_1);

	    coordinate_5.setCoordinate(units_m, units_m, units_m, new DoubleVector(new double[] {0.0, 0.0, 1.0}));

	    coordinate_5.setValue(new DoubleVector(new double[] {0.0, 0.0, -0.5*rotor_radius}));

	    // end coordinate
	    Coordinate coordinate_6 = 
	      simpleCylinderPart_0.getEndCoordinate();

	    coordinate_6.setCoordinateSystem(cartesianCoordinateSystem_1);

	    coordinate_6.setCoordinate(units_m, units_m, units_m, new DoubleVector(new double[] {0.0, 0.0, 0.0}));

	    coordinate_6.setValue(new DoubleVector(new double[] {0.0, 0.0, 0.5*rotor_radius}));

	    // radius
	    simpleCylinderPart_0.getRadius().setUnits(units_m);

	    simpleCylinderPart_0.getRadius().setValue(1.25*rotor_radius);

	    // misc
	    simpleCylinderPart_0.getTessellationDensityOption().setSelected(TessellationDensityOption.MEDIUM);

	    simpleCylinderPart_0.rebuildSimpleShapePart();

	    simpleCylinderPart_0.setDoNotRetessellate(false);



	    ///////////////////////////////////////////////////////////////////////////////
	    // SPHERE create the Shape Part
	    SimpleSpherePart simpleSpherePart_0 = 
	      meshPartFactory_0.createNewSpherePart(simulation_0.get(SimulationPartManager.class));
	      
	    simpleSpherePart_0.setPresentationName("refine sphere: " + name_VirtualDisks[i]);

	    simpleSpherePart_0.setDoNotRetessellate(true);

	    // coordinate system - attach to the Virtual Disk coordinate system
	    LabCoordinateSystem labCoordinateSystem_1 = 
	      simulation_0.getCoordinateSystemManager().getLabCoordinateSystem();

	    CartesianCoordinateSystem cartesianCoordinateSystem_0 = 
	      ((CartesianCoordinateSystem) labCoordinateSystem_1.getLocalCoordinateSystemManager().getObject(name_VirtualDisks[i] + "-CSys 1"));
	      
	    simpleSpherePart_0.setCoordinateSystem(cartesianCoordinateSystem_0);

	    Coordinate coordinate_9 = 
	      simpleSpherePart_0.getOrigin();

	    coordinate_9.setCoordinateSystem(cartesianCoordinateSystem_0);

	    coordinate_9.setCoordinate(units_m, units_m, units_m, new DoubleVector(new double[] {0.0, 0.0, 0.0}));

	    coordinate_9.setValue(new DoubleVector(new double[] {0.0, 0.0, 0.0}));
	    
	    simpleSpherePart_0.getRadius().setUnits(units_m);

	    simpleSpherePart_0.getRadius().setValue(4.50*rotor_radius);
    
	    simpleSpherePart_0.getTessellationDensityOption().setSelected(TessellationDensityOption.MEDIUM);

	    simpleSpherePart_0.rebuildSimpleShapePart();

	    simpleSpherePart_0.setDoNotRetessellate(false);

	    


	    ///////////////////////////////////////////////////////////////////////////////
	    // CONE create the Shape Part
	    SimpleConePart simpleConePart_0 = 
	      meshPartFactory_0.createNewConePart(simulation_0.get(SimulationPartManager.class));

	  	simpleConePart_0.setPresentationName("refine cone: " + name_VirtualDisks[i]);

	    simpleConePart_0.setDoNotRetessellate(true);

	    simpleConePart_0.setCoordinateSystem(cartesianCoordinateSystem_0);

	    Coordinate coordinate_2 = 
	      simpleConePart_0.getStartCoordinate();

	    coordinate_2.setCoordinateSystem(cartesianCoordinateSystem_0);

	    coordinate_2.setCoordinate(units_m, units_m, units_m, new DoubleVector(new double[] {0.0, 0.0, 1.0}));

	    coordinate_2.setValue(new DoubleVector(new double[] {0.0, 0.0, 0.0}));

	    simpleConePart_0.getStartRadius().setUnits(units_m);

	    simpleConePart_0.getStartRadius().setValue(1.25*rotor_radius);

	    Coordinate coordinate_3 = 
	      simpleConePart_0.getEndCoordinate();

	    coordinate_3.setCoordinateSystem(cartesianCoordinateSystem_0);

	    coordinate_3.setCoordinate(units_m, units_m, units_m, new DoubleVector(new double[] {0.0, 0.0, 0.0}));

	    coordinate_3.setValue(new DoubleVector(new double[] {0.0, 0.0, 10*2*rotor_radius}));

	    simpleConePart_0.getEndRadius().setUnits(units_m);

	    simpleConePart_0.getEndRadius().setValue(2.0*rotor_radius);

	    simpleConePart_0.getTessellationDensityOption().setSelected(TessellationDensityOption.MEDIUM);

	    simpleConePart_0.rebuildSimpleShapePart();

	    simpleConePart_0.setDoNotRetessellate(false);

	    

	    























	    ///////////////////////////////////////////////////////////////////////////////
	    // CYLINDER create a volumentric mesh control, add Shape Parts

	    AutoMeshOperation autoMeshOperation_0 = 
	      ((AutoMeshOperation) simulation_0.get(MeshOperationManager.class).getObject("Automated Mesh"));

	    VolumeCustomMeshControl volumeCustomMeshControl_2 = 
	      autoMeshOperation_0.getCustomMeshControls().createVolumeControl();

	      volumeCustomMeshControl_2.setPresentationName("refine cylinder: " + name_VirtualDisks[i]);

	      volumeCustomMeshControl_2.getGeometryObjects().setObjects(simpleCylinderPart_0);


	      // customize the size of this mesh refinement
	    VolumeControlTrimmerSizeOption volumeControlTrimmerSizeOption_2 = 
	      volumeCustomMeshControl_2.getCustomConditions().get(VolumeControlTrimmerSizeOption.class);

	    volumeControlTrimmerSizeOption_2.setVolumeControlBaseSizeOption(true);

	    VolumeControlSize volumeControlSize_2 = 
	      volumeCustomMeshControl_2.getCustomValues().get(VolumeControlSize.class);

	    volumeControlSize_2.getRelativeOrAbsoluteOption().setSelected(RelativeOrAbsoluteOption.ABSOLUTE);

	    GenericAbsoluteSize genericAbsoluteSize_2 = 
	      ((GenericAbsoluteSize) volumeControlSize_2.getAbsoluteSize());

	    genericAbsoluteSize_2.getValue().setValue(0.10*rotor_radius);
	  


	    ///////////////////////////////////////////////////////////////////////////////
	    // SPHERE create a volumentric mesh control, add Shape Parts
	    AutoMeshOperation autoMeshOperation_1 = 
	      ((AutoMeshOperation) simulation_0.get(MeshOperationManager.class).getObject("Automated Mesh"));

	    VolumeCustomMeshControl volumeCustomMeshControl_3 = 
	      autoMeshOperation_1.getCustomMeshControls().createVolumeControl();

	    volumeCustomMeshControl_3.setPresentationName("refine sphere: " + name_VirtualDisks[i]);

	    volumeCustomMeshControl_3.getGeometryObjects().setObjects(simpleSpherePart_0);

	    VolumeControlTrimmerSizeOption volumeControlTrimmerSizeOption_3 = 
	      volumeCustomMeshControl_3.getCustomConditions().get(VolumeControlTrimmerSizeOption.class);

	    volumeControlTrimmerSizeOption_3.setVolumeControlBaseSizeOption(true);

	    VolumeControlSize volumeControlSize_3 = 
	      volumeCustomMeshControl_3.getCustomValues().get(VolumeControlSize.class);

	    volumeControlSize_3.getRelativeOrAbsoluteOption().setSelected(RelativeOrAbsoluteOption.ABSOLUTE);

	    GenericAbsoluteSize genericAbsoluteSize_3 = 
	      ((GenericAbsoluteSize) volumeControlSize_3.getAbsoluteSize());

	    genericAbsoluteSize_3.getValue().setValue(0.50*rotor_radius);



		///////////////////////////////////////////////////////////////////////////
	    // CONE create a volumentric mesh control, add Shape Parts
	    VolumeCustomMeshControl volumeCustomMeshControl_0 = 
	      autoMeshOperation_0.getCustomMeshControls().createVolumeControl();

		volumeCustomMeshControl_0.setPresentationName("refine cone: " + name_VirtualDisks[i]);

	    volumeCustomMeshControl_0.getGeometryObjects().setObjects(simpleConePart_0);


	    VolumeControlTrimmerSizeOption volumeControlTrimmerSizeOption_0 = 
	      volumeCustomMeshControl_0.getCustomConditions().get(VolumeControlTrimmerSizeOption.class);

	    volumeControlTrimmerSizeOption_0.setVolumeControlBaseSizeOption(true);

	    VolumeControlSize volumeControlSize_1 = 
	      volumeCustomMeshControl_0.getCustomValues().get(VolumeControlSize.class);

	    volumeControlSize_1.getRelativeOrAbsoluteOption().setSelected(RelativeOrAbsoluteOption.ABSOLUTE);

	    GenericAbsoluteSize genericAbsoluteSize_1 = 
	      ((GenericAbsoluteSize) volumeControlSize_1.getAbsoluteSize());

	    genericAbsoluteSize_1.getValue().setValue(rotor_radius);




    	///////////////////////////////////////////////////////////////////////////
	    // POINT shape parts at seabed marking turbine locations
	    PointPart pointPart_0 = 
	      simulation_0.getPartManager().createPointPart(new NeoObjectVector(new Object[] {}), new DoubleVector(new double[] {0.0, 0.0, 0.0}));

	    Coordinate coordinate_point = 
	      pointPart_0.getPointCoordinate();

	    // LabCoordinateSystem labCoordinateSystem_0 = 
	    //   simulation_0.getCoordinateSystemManager().getLabCoordinateSystem();

	    // CartesianCoordinateSystem cartesianCoordinateSystem_0 = 
	    //   ((CartesianCoordinateSystem) labCoordinateSystem_0.getLocalCoordinateSystemManager().getObject("turbine  1: ccw-CSys 1"));

	    coordinate_point.setCoordinateSystem(cartesianCoordinateSystem_0);

	    coordinate_point.setValue(new DoubleVector(new double[] {0.0, -30.000000000000004, 0.0}));

	    coordinate_point.setCoordinate(units_m, units_m, units_m, new DoubleVector(new double[] {0.0, -28, 0.0}));

	    pointPart_0.setCoordinateSystem(cartesianCoordinateSystem_0);

	    pointPart_0.getInputParts().setObjects(region_0);

	    pointPart_0.setFollowMotion(true);

	    pointPart_0.setPresentationName("point on seabed: " + name_VirtualDisks[i]);

















	} // end FOR loop














		// dunno why but these things behave better in a separate loop

		Units units_none = 
		  simulation_0.getUnitsManager().getPreferredUnits(new IntVector(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));



		for (int i = 0; i < nVirtualDisks; i++) {

			    ///////////////////////////////////////////////////////////////////////////////
				// create threshold parts
			    PrimitiveFieldFunction primitiveFieldFunction_0 = 
			      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction(name_VirtualDiskMarker[i]));
			      // ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("VirtualDiskMarker" + i));
			      // ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("VirtualDiskMarker1"));			      
			      // ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("VirtualDiskMarker" + "i]"));

			    ThresholdPart thresholdPart_0 = 
			      simulation_0.getPartManager().createThresholdPart(new NeoObjectVector(new Object[] {region_0}), new DoubleVector(new double[] {1.0, 1.0}), units_none, primitiveFieldFunction_0, 0);

			    thresholdPart_0.setPresentationName(name_VirtualDisks[i]);


			    PrimitiveFieldFunction primitiveFieldFunction_1 = 
			      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction(name_VirtualDiskInflowPlaneMarker[i]));
			      // ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("VirtualDiskInflowPlaneMarker" + i));
			      // ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("VirtualDiskInflowPlaneMarker1"));
			      // ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("VirtualDiskInflowPlaneMarker" + "i]"));

			    ThresholdPart thresholdPart_1 = 
			      simulation_0.getPartManager().createThresholdPart(new NeoObjectVector(new Object[] {region_0}), new DoubleVector(new double[] {1.0, 1.0}), units_none, primitiveFieldFunction_1, 0);

			    thresholdPart_1.setPresentationName("inflow " + name_VirtualDisks[i]);




			    ///////////////////////////////////////////////////////////////////////////////
				// create volume average reports and monitors on the threshold parts
			    VolumeAverageReport volumeAverageReport_0 = 
			      simulation_0.getReportManager().createReport(VolumeAverageReport.class);

		      	// FvRepresentation fvRepresentation_0 = 
      			//   ((FvRepresentation) simulation_0.getRepresentationManager().getObject("Volume Mesh"));

    			// volumeAverageReport_0.setRepresentation(fvRepresentation_0);

			    PrimitiveFieldFunction primitiveFieldFunction_2 = 
			      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("Velocity"));

			    VectorMagnitudeFieldFunction vectorMagnitudeFieldFunction_0 = 
			      ((VectorMagnitudeFieldFunction) primitiveFieldFunction_2.getMagnitudeFunction());

			    volumeAverageReport_0.setScalar(vectorMagnitudeFieldFunction_0);

			    volumeAverageReport_0.getParts().setObjects(thresholdPart_1);

			    volumeAverageReport_0.setPresentationName("volume avg. inflow " + name_VirtualDisks[i]);


			    VolumeAverageReport volumeAverageReport_1 = 
			      ((VolumeAverageReport) simulation_0.getReportManager().getReport("volume avg. inflow " + name_VirtualDisks[i]));

			    ReportMonitor reportMonitor_0 = 
				  volumeAverageReport_1.createMonitor();




		} // end FOR loop




	} //end execute0




} // end execute()
