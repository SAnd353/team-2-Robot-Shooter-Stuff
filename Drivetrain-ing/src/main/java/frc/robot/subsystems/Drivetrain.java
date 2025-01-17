// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private WPI_VictorSPX m_leftFrontMotor = new WPI_VictorSPX(Constants.CAN.drive_lf);
  private WPI_TalonSRX m_leftBackMotor = new WPI_TalonSRX(Constants.CAN.drive_lb);
  public SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(m_leftFrontMotor, m_leftBackMotor);

  private WPI_VictorSPX m_rightFrontMotor = new WPI_VictorSPX(Constants.CAN.drive_rf);
  private WPI_TalonSRX m_rightBackMotor = new WPI_TalonSRX(Constants.CAN.drive_rb);
  public SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(m_rightFrontMotor, m_rightBackMotor);

  public DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  public PIDController m_pidController = new PIDController(0.09, 0, 0);

  public final Encoder m_leftEncoder = new Encoder(0,1);

  private NetworkTable m_table = NetworkTableInstance.getDefault().getTable("limelight");
  public NetworkTableEntry tx = m_table.getEntry("tx");
  public NetworkTableEntry ta = m_table.getEntry("ta");

  
  public Drivetrain() {
    m_leftEncoder.setDistancePerPulse(2 * Math.PI * 7.62 / -2048);
    m_leftEncoder.reset();
    // m_leftBackMotor.setInverted(true);
    // m_leftFrontMotor.setInverted(true);
  }
  
  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd * Constants.kDriveSpeed, -rot * Constants.kTurnSpeed, true);
  }
  public void tankDrive(double leftSpeed, double rightSpeed){
    m_drive.tankDrive(leftSpeed, rightSpeed);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
   public void stopMotors(){
    m_drive.arcadeDrive(0,0);
  }

  public void set(double current, double target) {
    m_leftFrontMotor.set(m_pidController.calculate(current, target) * -0.1);
    m_rightFrontMotor.set(m_pidController.calculate(current, target) * 0.1);
    m_leftBackMotor.set(m_pidController.calculate(current, target) * -0.1);
    m_rightBackMotor.set(m_pidController.calculate(current, target) * 0.1);
  }

  public void setDistance(double current, double target) {
    m_leftFrontMotor.set(m_pidController.calculate(current, target) * -0.5);
    m_rightFrontMotor.set(m_pidController.calculate(current, target) * 0.5);
    m_leftBackMotor.set(m_pidController.calculate(current, target) * -0.5);
    m_rightBackMotor.set(m_pidController.calculate(current, target) * 0.5);
  }

  public double distance(){
    if(m_leftEncoder.getDistance() != 0 || m_leftEncoder.getRate() != 0)
    System.out.println(m_leftEncoder.getDistance()*100 + " " + m_leftEncoder.getRate()*100);
    return m_leftEncoder.getDistance();
    
  }

  public double getEncoderValue() {
    // System.out.println(m_leftEncoder.getRate());
    return m_leftEncoder.getRate();
  }
  public PIDController m_VpidController = new PIDController(0.05, 0, 0);
  public void VisionAlign(){
    double x = tx.getDouble(0.0);
    boolean isPid = true;
    double rotation = isPid ? m_VpidController.calculate(x, 0) : x*.001;
    // tankDrive(rotation, -rotation);
    arcadeDrive(0, -rotation);
  }

  public PIDController m_LpidController = new PIDController(0.5, 0, 0);

    public void follow(){
      double l = ta.getDouble(0);

      if (Math.abs(l-.5) > .1){
        double speed = m_LpidController.calculate(l, 0.5);
        arcadeDrive(-speed,0);
      }
    }
}
