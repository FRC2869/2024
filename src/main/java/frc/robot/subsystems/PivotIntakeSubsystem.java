package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PivotIntakeSubsystem extends SubsystemBase {
    private static PivotIntakeSubsystem pivot;

    private SparkPIDController pid;
    private CANSparkMax sparkMax;
    private double position;
    private RelativeEncoder encoder;
    private boolean isMoving;

    public static PivotIntakeSubsystem getInstance() {
        if (pivot == null) pivot = new PivotIntakeSubsystem();
        return pivot;
    }

    public PivotIntakeSubsystem() {
        sparkMax = new CANSparkMax(0, MotorType.kBrushless);
        isMoving = false;
        encoder = sparkMax.getEncoder();
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
