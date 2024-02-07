package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PivotShooterSubsystem extends SubsystemBase {
    private static PivotShooterSubsystem pivot;
    
    private SparkPIDController pid;
    private double position;
    private RelativeEncoder encoder;
    private boolean isMoving;
    private CANSparkFlex sparkMax;

    public static PivotShooterSubsystem getInstance() {
        if (pivot == null) pivot = new PivotShooterSubsystem();
        return pivot;
    }

    public PivotShooterSubsystem() {
        configurePivotMotor();
    }

	private void configurePivotMotor() {
		// pivotMotor.restoreFactoryDefaults();
		pid = sparkMax.getPIDController();
        pid.setP(Constants.ShooterConstants.kP);
        pid.setI(Constants.ShooterConstants.kI);
        pid.setD(Constants.ShooterConstants.kD);
        pid.setIZone(Constants.ShooterConstants.kIz);
        pid.setFF(Constants.ShooterConstants.kF);
        pid.setOutputRange(Constants.ShooterConstants.kMinOutput, Constants.ShooterConstants.kMaxOutput);
	}

    public void setSpeed(double speed) {
        sparkMax.set(speed);
    }

    public void setPosition(double pos) {
        position = pos;
        isMoving = true;
    }

    @Override
    public void periodic() {
        if (isMoving) {
            if (encoder.getPosition() > position) {
                sparkMax.set(-.5);
            }
            else if (encoder.getPosition() < position) {
                sparkMax.set(.5);
            }
            else {
                isMoving = false;
            }
        }
    }
}
