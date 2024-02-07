// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import swervelib.math.Matter;
import swervelib.parser.PIDFConfig;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean constants. This
 * class should not be used for any other purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants
{

  public static final double ROBOT_MASS = (148 - 20.3) * 0.453592; // 32lbs * kg per pound
  public static final Matter CHASSIS    = new Matter(new Translation3d(0, 0, Units.inchesToMeters(8)), ROBOT_MASS);
  public static final double LOOP_TIME  = 0.13; //s, 20ms + 110ms sprk max velocity lag
  

  public static final class Auton
  {

    public static final PIDFConfig TranslationPID     = new PIDFConfig(0.7, 0, 0);
    public static final PIDFConfig angleAutoPID = new PIDFConfig(0.4, 0, 0.01);

    public static final double MAX_ACCELERATION = 2;
  }

  public static final class Drivebase
  {

    // Hold time on motor brakes when disabled
    public static final double WHEEL_LOCK_TIME = 10; // seconds
  }

  public static class OperatorConstants
  {

    // Joystick Deadband
    public static final double LEFT_X_DEADBAND = 0.01;
    public static final double LEFT_Y_DEADBAND = 0.01;
    public static final double RIGHT_X_DEADBAND = 0.01;
    public static final double TURN_CONSTANT = 0.75;
    public static final int driver1ControllerPort = 0;
    public static final int operatorControllerPort = 0;
    public static final int elevatorControllerPort = 0;
  }

  public static class ElevatorConstants
  {
    //SET THIS
    public static final double maxHeight = 10.25;
    public static final double minHeight = 0;
    public static final double speed = 0.5;
    public static final double midHeight = 5.125;
  }

  public static class ShooterConstants
  {
    public static final int leftID = 0;
    public static final int rightID = 0;
    public static final int ID775 = 0;
    public static final int kP = 0;
    public static final int kI = 0;
    public static final int kD = 0;
    public static double kMinOutput;
    public static double kF;
    public static double kMaxOutput;
    public static double kIz;
  }
  public static class IntakeConstants
  { 
    public static final int intakeChannel1 = 1;
    public static final int intakeChannel2 = 5;
    public static final double speed = 3.3;
  }

  public static class LimelightConstants
  {
    // LimeLight details
    public static final double lensHeight = 18.008712;
    public static final double lensFromCenter = 6.86;
    
    // AprilTag heights
    public static final double sourceTags = 48.125;
    public static final double speakerTags = 51.875;
    public static final double ampTags = 48.125;
    public static final double stageTags = 47.5;

    public static final double errorX = 0;
    public static final double errorY = 0;

    // Other
    public static final double distance = 0;
  }
  
  public static class PivotConstants {
    public static final double intakeFloorPosition = 0;
    public static final double intakeTransferPosition = 0;
    public static final double shooterTransferPosition = 0;
    public static final double shooterShootPosition = 0;
    public static final double kP = 0;
    public static final double kI = 0;
    public static final double kD = 0;
    public static final double kF = 0;
    public static final double kMaxAutoPower = 0;
  }
}
