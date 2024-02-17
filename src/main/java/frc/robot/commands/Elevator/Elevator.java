// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.Inputs;

// public class Elevator extends Command {
//   private final ElevatorSubsystem elevator = ElevatorSubsystem.getInstance();
//   /** Creates a new Elevator. */
//   public Elevator() {
//     addRequirements(elevator);
//     // Use addRequirements() here to declare subsystem dependencies.
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {}

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     if (Inputs.getOverride().getAsBoolean()) {
//             elevator.setSpeed(Inputs.getOverrideY() * 0.25);
//         } else if (Inputs.elevatorMax().getAsBoolean() && !Inputs.elevatorMin().getAsBoolean()) {
//             if (elevator.getPosition() < ElevatorConstants.maxHeight) {
//                 elevator.setSpeed(ElevatorConstants.speed);
//               }
//               else if (elevator.getPosition() > ElevatorConstants.maxHeight) {
//                 elevator.setSpeed(-ElevatorConstants.speed);
//               } else {
//                 elevator.setSpeed(0);
//               }
//         } else if (Inputs.elevatorMin().getAsBoolean() && !Inputs.elevatorMax().getAsBoolean()) {
//             if (elevator.getPosition() < ElevatorConstants.minHeight) {
//                 elevator.setSpeed(ElevatorConstants.speed);
//               }
//               else if (elevator.getPosition() > ElevatorConstants.minHeight) {
//                 elevator.setSpeed(-ElevatorConstants.speed);
//               } else {
//                 elevator.setSpeed(0);
//               }
//         } else if (Inputs.elevatorMax().getAsBoolean() && Inputs.elevatorMin().getAsBoolean()) {
//             if (elevator.getPosition() < ElevatorConstants.midHeight) {
//                 elevator.setSpeed(ElevatorConstants.speed);
//               }
//               else if (elevator.getPosition() > ElevatorConstants.midHeight) {
//                 elevator.setSpeed(-ElevatorConstants.speed);
//               } else {
//                 elevator.setSpeed(0);
//               }
//         }
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {}

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }
