using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Polygon
{
    [Serializable]
    public class Polygon
    {
        public List<Point> points { get; set; }
        public Color Col { get; set; }
        public int Thickness { get; set; } = 2;

        public Polygon(List<Point> points, Color color, int thickness)
        {
            this.points = points;
            Col = color;
            Thickness = thickness;
        }
        public void Draw(Graphics g)
        {
            Brush brush = new SolidBrush(Col);
            g.FillPolygon(brush, points.ToArray());
            brush.Dispose();
        }
    }
}
