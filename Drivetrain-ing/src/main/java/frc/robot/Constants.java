// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public final class PID{
        
        kP = 0.35; 
        kI = 0;
        kD = 0; 
        kIz = 0; 
        kFF = 0.000015; 
        kMaxOutput = 1; 
        kMinOutput = -1;
        maxRPM = 5700;
    }
    public static final int drive_lf = 24;
    public static final int drive_lb = 13;
    public static final int drive_rf = 23;
    public static final int drive_rb = 15;

    public static final double kDriveSpeed = 1.0;
    public static final double kTurnSpeed = 1.0;

    public static final double autoSpeed = 0.8;
    public static final double autoSpeed2 = 0.8;
    public static final double autoRotation = 0.7;

    public static final double autoSeconds = 1;
    }
