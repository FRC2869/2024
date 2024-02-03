// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.PivotIntakeSubsystem;
import frc.robot.subsystems.PivotShooterSubsystem;

public class PivotShoot extends Command {
  PivotShooterSubsystem shootPivot = PivotShooterSubsystem.getInstance();

  /** Creates a new floorPickup. */
  public PivotShoot() {
    addRequirements(shootPivot);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shootPivot.setPosition(Constants.PivotConstants.shooterShootPosition);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}