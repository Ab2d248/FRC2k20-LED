/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import java.awt.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TwoColorLED extends SubsystemBase {
  /**
   * Creates a new TwoColorLED.
   */
  public static AddressableLED led;
  public static AddressableLEDBuffer m_buffer;
  public static Color purple, gold;
  public Color[] color;
  public Color temp;
  
  public TwoColorLED() {
    led = new AddressableLED(1);
    m_buffer = new AddressableLEDBuffer(178);
    led.setLength(m_buffer.getLength());
    led.start();

    purple = new Color(84, 0, 84);
    gold = new Color(240, 100, 0);

    for(int i = 0; i < m_buffer.getLength(); i++)
    {
      if(i < m_buffer.getLength()/2)
        m_buffer.setRGB(i, gold.getRed(), gold.getGreen(), gold.getBlue());
      else
        m_buffer.setRGB(i, purple.getRed(), purple.getGreen(), purple.getBlue());
    }

    led.setData(m_buffer);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
