// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.swervedrive;
import edu.wpi.first.*;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightSubsystem extends SubsystemBase {

  private NetworkTable table;
  
  NetworkTableEntry tx;
  
  SwerveSubsystem swerve;

  //tid primary april tag
  /** Creates a new LimelightSubsystem. */
  public LimelightSubsystem() {
    swerve = SwerveSubsystem.getInstance();
    table = NetworkTableInstance.getDefault().getTable("limelight");
    tx = table.getEntry("tx");
    table.getEntry("targetpose_cameraspace").getDoubleArray(new double[6]);
  }
  //driveCommand
  @Override
  public void periodic() {
    
  }
}
