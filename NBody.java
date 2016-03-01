public class NBody {
  public static double readRadius(String file_name) {
    In in = new In(file_name);
    in.readInt();
    return in.readDouble();
  }
  public static Planet[] readPlanets(String file_name) {
    In in = new In(file_name);
    int num = in.readInt();
    in.readDouble();
    Planet[] array = new Planet[num];
    int index = 0;
    while (num > index) {
      Planet new_plan = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), "images/" + in.readString());
      array[index] = new_plan;
      index++;
    }
    return array;
  }
  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double r = readRadius(filename);
    Planet[] planets = readPlanets(filename);
    StdDraw.setScale(-r, r);
    StdDraw.picture(0, 0, "images/starfield.jpg");
    int num = 0;
    for(Planet planet: planets) {
      planet.draw();
      num++;
    }
    double time = 0;
    while (time != T) {
      double[] xForces = new double[num];
      double[] yForces = new double[num];
      int index = 0;
      for(Planet planet: planets) {
        xForces[index] = planet.calcNetForceExertedByX(planets);
        yForces[index] = planet.calcNetForceExertedByY(planets);
        index++;
      }
      int i = 0;
      for(Planet planet: planets) {
        planet.update(dt, xForces[i], yForces[i]);
        i++;
      }
      StdDraw.picture(0, 0, "images/starfield.jpg");
      for(Planet planet: planets) {
        planet.draw();
      }
      StdDraw.show(10);
      time += dt;
    }
  }
}
