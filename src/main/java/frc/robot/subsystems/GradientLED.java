/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import java.awt.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class GradientLED extends SubsystemBase {
  /**
   * Creates a new GradientLED.
   */

  public static AddressableLED led;
  public static AddressableLEDBuffer m_buffer;
  public static Color gold;
  public int counter;

  public GradientLED() {
    led = new AddressableLED(1);
    m_buffer = new AddressableLEDBuffer(178);
    led.setLength(m_buffer.getLength());
    led.start();

    gold = new Color(240, 100, 0);
    counter = 0;

    for(int i = 0; i < m_buffer.getLength(); i++)
    {
      m_buffer.setRGB(i, 0, 0, 0);
    }

    led.setData(m_buffer);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    if(counter < m_buffer.getLength())
    {
      m_buffer.setRGB(counter, gold.getRed() - counter, gold.getGreen() - counter, counter);
      counter++;
    }
    else
    {
      counter = 0;
      for(int i = 0; i < m_buffer.getLength(); i++)
      {
        m_buffer.setRGB(i, 0, 0, 0);
      }
    }
    led.setData(m_buffer);
    Timer.delay(0.05);

  }
}
