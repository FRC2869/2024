package frc.robot.subsystems.swervedrive;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.Constants;

public class ShooterSubsystem {
    
    private static ShooterSubsystem shoot = null;

    private TalonFX leftTalon;
    private TalonFX rightTalon;

    public ShooterSubsystem() {
        leftTalon = new TalonFX(Constants.ShooterConstants.leftID);
        rightTalon = new TalonFX(Constants.ShooterConstants.rightID);
        init();
    }

    public static ShooterSubsystem getInstance() {
        if (shoot == null) shoot = new ShooterSubsystem();
        return shoot;
    }
    
    public void init() {
        TalonFXConfigurator configs = leftTalon.getConfigurator();
        Slot0Configs slotCons = new Slot0Configs();
        slotCons.kP = Constants.ShooterConstants.kP;
        slotCons.kI = Constants.ShooterConstants.kI;
        slotCons.kD = Constants.ShooterConstants.kD;
        configs.apply(slotCons);
    }

    public void shoot() {
        leftTalon.setVoltage(5);
    }
    
    public void intake() {
        leftTalon.set(.2);
        rightTalon.set(-.2);
    }
}