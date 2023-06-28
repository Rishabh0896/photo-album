package model;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public enum ShapeType {
  OVAL {
    @Override
    public void appendToBuilder(StringBuilder builder, IShape shape, String shapeName) {
      builder.append("<ellipse id=\"").append(shapeName).append("\" ")
              .append("cx=\"").append(shape.getPivot().getCoordinateX()).append("\" ")
              .append("cy=\"").append(shape.getPivot().getCoordinateY()).append("\" ")
              .append("rx=\"").append(shape.getDimensionOne()).append("\" ")
              .append("ry=\"").append(shape.getDimensionTwo()).append("\" ")
              .append("fill=\"rgb(").append(shape.getColor().getRedComp()).append(",")
              .append(shape.getColor().getGreenComp()).append(",")
              .append(shape.getColor().getBlueComp()).append(")\" ")
              .append("visibility=\"visible\">").append("\n").append("</ellipse>");
    }

    @Override
    public Graphics2D drawShape(IShape shape, Graphics2D g2d) {
      Ellipse2D.Double ellipse = new Ellipse2D.Double(shape.getPivot().getCoordinateX(),
              shape.getPivot().getCoordinateY(), shape.getDimensionOne(),
              shape.getDimensionTwo());
      g2d.setColor(new Color((int) shape.getColor().getRedComp(),
              (int) shape.getColor().getGreenComp(),
              (int) shape.getColor().getBlueComp()));
      g2d.fill(ellipse);
      return g2d;
    }
  },
  RECTANGLE {
    @Override
    public void appendToBuilder(StringBuilder builder, IShape shape, String shapeName) {
      builder.append("<rect id=\"").append(shapeName).append("\" ")
              .append("x=\"").append(shape.getPivot().getCoordinateX()).append("\" ")
              .append("y=\"").append(shape.getPivot().getCoordinateY()).append("\" ")
              .append("width=\"").append(shape.getDimensionOne()).append("\" ")
              .append("height=\"").append(shape.getDimensionTwo()).append("\" ")
              .append("fill=\"rgb(").append(shape.getColor().getRedComp()).append(",")
              .append(shape.getColor().getGreenComp()).append(",")
              .append(shape.getColor().getBlueComp()).append(")\" ")
              .append("visibility=\"visible\">").append("\n").append("</rect>");
    }

    @Override
    public Graphics2D drawShape(IShape shape, Graphics2D g2d) {
      Rectangle2D.Double rectangle = new Rectangle2D.Double(shape.getPivot().getCoordinateX(),
              shape.getPivot().getCoordinateY(), shape.getDimensionOne(),
              shape.getDimensionTwo());
      g2d.setColor(new Color((int) shape.getColor().getRedComp(),
              (int) shape.getColor().getGreenComp(),
              (int) shape.getColor().getBlueComp()));
      g2d.fill(rectangle);
      return g2d;
    }

  };

  public abstract void appendToBuilder(StringBuilder builder, IShape shape, String shapeName);

  public abstract Graphics2D drawShape(IShape shape, Graphics2D g2d);
}
