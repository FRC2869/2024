// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Inputs;
import frc.robot.subsystems.ElevatorSubsystem;

public class DefaultElevatorCommand extends Command {
  private ElevatorSubsystem elevator;

  /** Creates a new DefaultElevatorCommand. */
  public DefaultElevatorCommand() {
    elevator = ElevatorSubsystem.getInstance();
    addRequirements(elevator);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    elevator.setPositionControl(false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // System.out.println(Inputs.getManualElevatorSpeed());
    elevator.setSpeed(Inputs.getManualElevatorSpeed());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    elevator.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
