// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
// DASH U GENIUS
// DASH U CARRY
// #teamdash

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;

/** An example command that uses an example subsystem. */
public class Autonomous2TimedLines extends CommandBase {

  private final Timer m_timer = new Timer();
  private final Drivetrain m_drive;

  public Autonomous2TimedLines(Drivetrain drivetrain) {
    m_drive = drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.reset();
    m_timer.start();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double time = m_timer.get();
    if (time < 1) {
      m_drive.arcadeDrive(Constants.autoSpeed2, 0);

    } else if (time < 1.5) {
      m_drive.arcadeDrive(0, 0.8);

    } else if (time < 3.5) {
      m_drive.arcadeDrive(Constants.autoSpeed2, 0);

    } else if (time < 4) {
      m_drive.arcadeDrive(0, -0.8);

    } else if (time < 5) {
      m_drive.arcadeDrive(Constants.autoSpeed2, 0);

    } else if (time < 5.5) {
      m_drive.arcadeDrive(0, -0.8);

    } else if (time < 6.5) {
      m_drive.arcadeDrive(Constants.autoSpeed2, 0);

    } else if (time < 7) {
      m_drive.arcadeDrive(0, -0.8);

    } else if (time < 9) {
      m_drive.arcadeDrive(Constants.autoSpeed2, 0);

    } else if (time < 9.5) {
      m_drive.arcadeDrive(0, 0.8);

    } else if (time < 10) {
      m_drive.arcadeDrive(Constants.autoSpeed2, 0);

    }else{
    m_drive.stopMotors();
  }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}