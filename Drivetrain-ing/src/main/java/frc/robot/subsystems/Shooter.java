package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.controller.PIDController;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Shooter extends SubsystemBase {
    private CANSparkMax m_motor;
    private CANEncoder m_encoder;
    private CANPIDController m_controller;
    private PIDController m_pidController;
    
    private WPI_TalonSRX m_belt_f = new WPI_TalonSRX(Constants.CAN.shooter_belt_front);
    private VictorSPX m_belt_b = new VictorSPX(Constants.CAN.shooter_belt_back);

   
public Shooter(){
    m_motor = new CANSparkMax(10, MotorType.kBrushless);
    m_encoder = m_motor.getEncoder();
    m_controller = m_motor.getPIDController();
    m_motor.restoreFactoryDefaults();

    stop();


    m_pidController = new PIDController(Constants.PID.kP, Constants.PID.kI, Constants.PID.kD);
  
    m_controller.setP(Constants.PID.kP);
    m_controller.setI(Constants.PID.kI);
    m_controller.setD(Constants.PID.kD);
    m_controller.setIZone(Constants.PID.kIz);
    m_controller.setFF(Constants.PID.kFF);
    m_controller.setOutputRange(Constants.PID.kMinOutput, Constants.PID.kMaxOutput);
}

public double getEncoderValue() {
    return m_encoder.getVelocity();
  }

public void set(double current, double target) {
    m_motor.set(m_pidController.calculate(current, target) * 0.004);
    // System.out.println(m_pidController.calculate(current, target) * 0.004);
  }

public void stop(){
    m_motor.stopMotor();
}

 /**
   * Run belt up
   */
  public void beltUp() {
    m_belt_f.set(ControlMode.PercentOutput, Constants.kBeltSpeed);
    m_belt_b.set(ControlMode.PercentOutput, Constants.kBeltSpeed);
  }
  
  /**
   * Run belt down
   */
  public void beltDown() {
    m_belt_f.set(ControlMode.PercentOutput, -Constants.kBeltSpeed);
    m_belt_b.set(ControlMode.PercentOutput, -Constants.kBeltSpeed);
  }

  /**
   * Stop belt
   */
  public void beltStop() {
    m_belt_f.set(ControlMode.PercentOutput, 0);
    m_belt_b.set(ControlMode.PercentOutput, 0);
  }
}