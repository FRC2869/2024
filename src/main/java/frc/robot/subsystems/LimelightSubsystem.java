// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Inputs;

//IDC abt this class rn
public class LimelightSubsystem extends SubsystemBase {

  private static LimelightSubsystem instance;

  private NetworkTable table;
  private NetworkTableEntry transform;

  private double vx;
  private double vy;
  private double angle;

  public static LimelightSubsystem getInstance() {
    if (instance == null) instance = new LimelightSubsystem();
    return instance;
  }

  //tid primary april tag
  /** Creates a new LimelightSubsystem. */
  public LimelightSubsystem() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    transform = table.getEntry("targetpose_cameraspace");
  }

  public double[] getTransform() {
    return transform.getDoubleArray(new double[6]);
  }
}
