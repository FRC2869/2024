package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.ShooterConstants.PositionsShooter;

public class PivotShooterSubsystem extends SubsystemBase {
    private static PivotShooterSubsystem pivot;

    private SparkPIDController pid;
    private CANSparkFlex pivotMotor;
    private double pos;
    private RelativeEncoder encoder;
    private boolean isPosControl = false;

    private double speed;

    private PositionsShooter currentPos;

    public static PivotShooterSubsystem getInstance() {
        if (pivot == null) pivot = new PivotShooterSubsystem();
        return pivot;
    }

    public PivotShooterSubsystem() {
        pivotMotor = new CANSparkFlex(ShooterConstants.PivotID, MotorType.kBrushless);
        isPosControl = false;
        encoder = pivotMotor.getEncoder();
        configurePivotMotor();
    }

	private void configurePivotMotor() {
		pivotMotor.restoreFactoryDefaults();
		pid = pivotMotor.getPIDController();
        pid.setP(ShooterConstants.kP);
        pid.setI(ShooterConstants.kI);
        pid.setD(ShooterConstants.kD);
        pid.setIZone(ShooterConstants.kIz);
        pid.setFF(ShooterConstants.kF);
        pid.setOutputRange(ShooterConstants.kMinOutput, ShooterConstants.kMaxOutput);
        encoder.setPositionConversionFactor(ShooterConstants.gearRatio);
        encoder.setPosition(ShooterConstants.startingPosition);
	}

    public void setPos(double pos) {
        this.pos = MathUtil.clamp(pos, ShooterConstants.kMinAngle, ShooterConstants.kMaxAngle);
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void resetPivot() {
        encoder.setPosition(ShooterConstants.startingPosition);
    }

    public double getAngle(){
        return encoder.getPosition();
    }

    public double getVelocity(){
        return encoder.getVelocity();
    }

    public boolean isAtPosition(){
		if(currentPos != PositionsShooter.STORAGE2)
			return Math.abs(pos-getAngle())<.5;
		else 
			return pos<ShooterConstants.getTargetPos(PositionsShooter.STORAGE2);
	}

    public void adjustUp() {
		pos += 2;
	}

	public void adjustDown() {
		pos -= 2;
	}

    public void setCurrentPosition(PositionsShooter pos) {
		currentPos = pos;
	}

    public void savePositions() {
		switch (currentPos) {
			default:
				break;

		}
    }

    @Override
    public void periodic() {
        if (isPosControl) {
            pivotMotor.getPIDController().setReference(pos, ControlType.kPosition);
        }else{
            pivotMotor.set(speed);
        }
    }
}
