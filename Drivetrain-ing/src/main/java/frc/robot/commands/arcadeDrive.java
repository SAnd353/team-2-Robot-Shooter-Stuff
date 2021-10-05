package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An autonomous command to arcade drive
 * 
 * @author Dashhhhhhh the Hard Carry
 * @author some random kid that sits on his computer pretending to know whats
 *         going on
 * @author also the other people
 */
public class arcadeDrive extends CommandBase {
  private final Drivetrain m_drive;
  private final DoubleSupplier m_forward;
  private final DoubleSupplier m_rotation;

  /**
   * 
   * @param drive
   * @param forward
   * @param rotation
   */
  public arcadeDrive(Drivetrain drive, DoubleSupplier forward, DoubleSupplier rotation) {
    m_drive = drive;
    m_forward = forward;
    m_rotation = rotation;
    addRequirements(m_drive);
  }

  @Override
  public void execute() {
    m_drive.arcadeDrive(m_forward.getAsDouble(), m_rotation.getAsDouble());
  }
}