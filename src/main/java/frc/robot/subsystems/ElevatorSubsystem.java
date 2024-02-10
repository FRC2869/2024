package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
    
    private static ElevatorSubsystem elev;

    private CANSparkMax elevator1;
    private CANSparkMax elevator2;

    private RelativeEncoder encoder1;
    private RelativeEncoder encoder2;

    private double speed = 0;

    private double pos;

    private boolean posControl;

    public ElevatorSubsystem () {
        elevator1 =  new CANSparkMax(0, MotorType.kBrushless);
        elevator2 =  new CANSparkMax(0, MotorType.kBrushless);
        encoder1 = elevator1.getEncoder();
        encoder2 = elevator2.getEncoder();
        elevator1.follow(elevator2, true);
        //REVIEW: Look at 2023 code and add the initalize sparks function
    }

    public static ElevatorSubsystem getInstance() {
        if (elev == null) elev = new ElevatorSubsystem();
        return elev;
    }
    //REVIEW: Format your subsystems like this. Have functions write their values to variables and only do .set once in periodic.
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setPos(double pos) {
        this.pos = pos;
    }

    public void setPositionControl(boolean posControl){
        this.posControl = posControl;
    }

    public double getPosition() {
        return (encoder1.getPosition()+encoder2.getPosition())/2;
    }

    @Override
    public void periodic() {
        if(posControl){
            //This is how to set position on Sparks
            elevator2.getPIDController().setReference(pos, ControlType.kPosition);
        }else{
            elevator2.set(-speed);
        }
    }
}
