package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

/**
 * Shooter belt up
 * 
 * @author Raadwan Masum, Rohan Juneja, Aadit Gupta
 * @author also Raquib Alam, Dash Penning, and Steven Zhu who copy and pasted
 *
 */
public class ShooterBeltUp extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final Shooter m_shooter;

  public ShooterBeltUp(Shooter shooter) {
    m_shooter = shooter;
    addRequirements(shooter);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    m_shooter.beltUp();
  }

  @Override
  public void end(boolean interrupted) {
    m_shooter.beltStop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}