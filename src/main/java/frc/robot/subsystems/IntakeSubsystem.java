// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

//REIVEW: Add a function to see if a game piece is in the intake
public class IntakeSubsystem extends SubsystemBase {

  private static IntakeSubsystem instance;
  /** Creates a new IntakeSubsystem. */
  private CANSparkMax rightTalon;
  private CANSparkMax leftTalon;
  

  public IntakeSubsystem() {
    leftTalon = new CANSparkMax(IntakeConstants.intakeID1, MotorType.kBrushed);
    rightTalon = new CANSparkMax(IntakeConstants.intakeID2, MotorType.kBrushed);
  }

  public void intake() {
      leftTalon.set(.5);
      rightTalon.set(.5);
  }

  public void outtake() {
      leftTalon.set(-.5);
      rightTalon.set(-.5);
  }

  public void stop() {
    leftTalon.set(0);
    rightTalon.set(0);
  }

  public static IntakeSubsystem getInstance() {
    if (instance == null) instance = new IntakeSubsystem();
    return instance;
  }
}
