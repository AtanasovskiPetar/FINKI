using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Polygon
{
    [Serializable]
    public class Line
    {
        public Point Point1 { get; set; }
        public Point Point2 { get; set; }
        public int Thickness { get; set; } = 2;
        public Color Col { get; set; }

        public Line(Point point1, Point point2, Color color, int thickness)
        {
            Point1 = point1;
            Point2 = point2;
            Col = color;
            Thickness = thickness;
        }
        public void Draw(Graphics g)
        {
            Pen pen = new Pen(Col);
            pen.Width = Thickness;
            g.DrawLine(pen, Point1, Point2);
        }

    }
}
