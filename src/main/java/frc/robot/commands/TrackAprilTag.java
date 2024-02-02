// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Inputs;
import frc.robot.subsystems.swervedrive.LimelightSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class TrackAprilTag extends Command {

  private SwerveSubsystem swerve = SwerveSubsystem.getInstance();
  private LimelightSubsystem limelight = LimelightSubsystem.getInstance();

  private double[] transformArray;
  private double vx;
  private double vy;
  private double angle;
  private double error;

  /** Creates a new TrackAprilTag. */
  public TrackAprilTag() {
    addRequirements(swerve, limelight);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    swerve.resetOdometry(null);
    transformArray = limelight.getTransform();
    vx = 0;
    vy = 0;
    angle = 0;
    if (transformArray[0] > Constants.LimelightConstants.lensFromCenter) {
      vx = .5;
    } else if (transformArray[0] < Constants.LimelightConstants.lensFromCenter) {
      vx = -.5;
    } else if (transformArray[1] > Constants.LimelightConstants.distance) {
      vy = .5;
    }
    swerve.driveCommand(() -> vx, () -> vy, () -> angle);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (transformArray[0] < error && transformArray[0] > -error && transformArray[1] < Constants.LimelightConstants.distance) {
      return true;
    }
    return false;
  }
}
