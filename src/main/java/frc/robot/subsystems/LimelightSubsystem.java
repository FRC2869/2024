// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.CommandSwerveDrivetrain;
import frc.robot.generated.TunerConstants;

//IDC abt this class rn
public class LimelightSubsystem extends SubsystemBase {

  private static LimelightSubsystem instance;

  private NetworkTable table;
  private NetworkTableEntry transform;
  private NetworkTableEntry mainID;
  private CommandSwerveDrivetrain drive;

  public static LimelightSubsystem getInstance() {
    if (instance == null) instance = new LimelightSubsystem();
    return instance;
  }

  //tid primary april tag
  /** Creates a new LimelightSubsystem. */
  public LimelightSubsystem() {
    drive = TunerConstants.DriveTrain;
    table = NetworkTableInstance.getDefault().getTable("limelight");
    transform = table.getEntry("targetpose_cameraspace");
    mainID = table.getEntry("tid");
  }

  public double[] getTransform() {
    return transform.getDoubleArray(new double[6]);
  }
  //addVisionMeasurements()
  public Pose2d getGoToPosition() {
    double x = 0;
    double y = 0;
    if (mainID.getInteger(-1) == 0) {
      
    }
    return new Pose2d(x, y, null);
  }
}
