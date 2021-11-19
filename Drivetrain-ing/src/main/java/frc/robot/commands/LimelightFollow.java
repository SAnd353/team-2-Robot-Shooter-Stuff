// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class LimelightFollow extends CommandBase {

  private final Drivetrain m_drive;
  // private final Shooter m_shooter;




  public LimelightFollow(Drivetrain drivetrain) {
    m_drive = drivetrain;

    addRequirements(m_drive);
  }


  @Override
  public void initialize() {
  }

 
  @Override
  public void execute() {
    m_drive.VisionAlign();
    m_drive.follow();
  }


  @Override
  public void end(boolean interrupted) {
  }


  @Override
  public boolean isFinished() {
    return false;
  }
}
