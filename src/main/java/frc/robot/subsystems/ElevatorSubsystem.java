package frc.robot.subsystems.swervedrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Inputs;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {
    
    private static ElevatorSubsystem elev;

    private CANSparkMax elevator1;
    private CANSparkMax elevator2;

    private RelativeEncoder encoder;

    public ElevatorSubsystem () {
        elevator1 =  new CANSparkMax(0, MotorType.kBrushless);
        elevator2 =  new CANSparkMax(0, MotorType.kBrushless);
        encoder = elevator1.getEncoder();
    }

    public static ElevatorSubsystem getInstance() {
        if (elev == null) elev = new ElevatorSubsystem();
        return elev;
    }

    public void setSpeed(double speed) {
        elevator1.set(speed);
        elevator2.set(-speed);
    }

    public double getPosition() {
        return encoder.getPosition();
    }
}
