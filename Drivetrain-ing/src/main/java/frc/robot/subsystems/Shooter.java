package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.controller.PIDController;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter extends SubsystemBase {
    private CANSparkMax m_motor;
    private CANEncoder m_encoder;
    private CANPIDController m_controller;
    private PIDController m_pidController;

   
public Shooter(){
    m_motor = new CANSparkMax(10, MotorType.kBrushless);
    m_encoder = m_motor.getEncoder();
    m_controller = m_motor.getPIDController();
    m_motor.restoreFactoryDefaults();

    stop();


    m_pidController = new PIDController(kP, kI, kD);
  
    m_controller.setP(kP);
    m_controller.setI(kI);
    m_controller.setD(kD);
    m_controller.setIZone(kIz);
    m_controller.setFF(kFF);
    m_controller.setOutputRange(kMinOutput, kMaxOutput);
}





public void set(double current, double target) {
    m_motor.set(m_pidController.calculate(current, target) * 0.004);
    // System.out.println(m_pidController.calculate(current, target) * 0.004);
  }

public void stop(){
    m_motor.stopMotor();
}
}
