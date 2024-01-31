// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.swervedrive;
import java.util.function.DoubleSupplier;

import edu.wpi.first.*;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Inputs;

public class LimelightSubsystem extends SubsystemBase {

  private NetworkTable table;
  
  private NetworkTableEntry transform;
  
  private SwerveSubsystem swerve;

  private double[] transformArray;

  private double vx;
  private double vy;
  private double angle;

  //tid primary april tag
  /** Creates a new LimelightSubsystem. */
  public LimelightSubsystem() {
    swerve = SwerveSubsystem.getInstance();
    table = NetworkTableInstance.getDefault().getTable("limelight");
    transform = table.getEntry("targetpose_cameraspace");
  }
  //driveCommand
  @Override
  public void periodic() {
    if (Inputs.getLimelight()) {
      swerve.resetOdometry(null);
      transformArray = transform.getDoubleArray(new double[6]);
      vx = 0;
      vy = 0;
      angle = 0;
      if (transformArray[0] > 0) {
        vx = .5;
      } else if (transformArray[0] < Constants.LimelightConstants.lensFromCenter) {
        vx = -.5;
      } else if (transformArray[1] > Constants.LimelightConstants.distance) {
        vy = .5;
      }

      swerve.driveCommand(() -> vx, () -> vy, () -> angle);
    }
  }
}
