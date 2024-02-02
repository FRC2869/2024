// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.swervedrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Inputs;

public class IntakeSubsystem extends SubsystemBase {

  private static IntakeSubsystem instance;
  /** Creates a new IntakeSubsystem. */
  private WPI_TalonSRX rightTalon;
  private WPI_TalonSRX leftTalon;
  

  public IntakeSubsystem() {
    leftTalon = new WPI_TalonSRX(Constants.IntakeConstants.intakeChannel1);
    rightTalon = new WPI_TalonSRX(Constants.IntakeConstants.intakeChannel2);
  }

  public void intake() {
      leftTalon.set(.5);
      rightTalon.set(.5);
  }

  public void stop() {
    leftTalon.set(0);
    rightTalon.set(0);
  }

  public static IntakeSubsystem getInstance() {
    if (instance == null) instance = new IntakeSubsystem();
    return instance;
  }

    @Override
  public void periodic() {
    if (Inputs.intake()) {
      intake();
    }
    else {
      stop();
    }
  }
}
