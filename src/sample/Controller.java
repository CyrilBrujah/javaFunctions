package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Controller {

    @FXML
    Canvas canvas;

    @FXML
    public void initialize() {
        canvas.setWidth(1600);
        canvas.setHeight(1000);
        GraphicsContext gc = canvas.getGraphicsContext2D();


        gc.setStroke(Color.BLACK);
        gc.setFill(Color.LIGHTCYAN);
        gc.setLineWidth(3);


        drawCircles(gc);
        drawSectors(gc);


        // test(gc);

    }

    double big = 420*2, medium1 = 280*2, medium2 = medium1-40, small = 140*2;
    double centerX = 0+big/2, centerY = 0+big/2;
    double offsetY = big*0.056;
    double center2Y = centerY-offsetY;

    private void drawCircles(GraphicsContext gc){
        double lw = gc.getLineWidth();
        gc.setLineWidth(4);
        gc.strokeOval(centerX-big/2,centerY-big/2,big,big);
        gc.strokeOval(centerX-medium1/2,center2Y-medium1/2,medium1, medium1);
        gc.strokeOval(centerX-medium2/2,center2Y-medium2/2,medium2, medium2);
        gc.strokeOval(centerX-small/2,center2Y-small/2,small,small);

        gc.setLineWidth(lw);
    }

    private void drawSectors(GraphicsContext gc){
//        double rSmall = medium1/2, rMedium = big/2, rBig = big/2;
//        gc.beginPath();
//        gc.moveTo(centerX-rSmall,center2Y);
//        gc.arcTo(centerX-rSmall,center2Y-rSmall,centerX,center2Y-rSmall,rSmall);
//        gc.lineTo(centerX,centerY-rBig);
//        gc.arcTo(0, 0, 0, center2Y, rBig);
//        //gc.lineTo(centerX-medium2/2, center2Y);
//        gc.lineTo(centerX-rSmall, center2Y);

        gc.fill();
        gc.stroke();
        gc.closePath();
        drawSector(gc, Color.LIGHTBLUE, centerX,center2Y,small/2, 5, 90,
                centerX,center2Y,medium2/2,3,90);

        drawSector(gc, Color.LIGHTGREEN, centerX,center2Y,medium1/2, 4, 90,
                centerX,centerY,big/2,10,90);
        //==================================================================================
        drawSector(gc, Color.LIGHTBLUE, centerX,center2Y,small/2, 90, 270,
                centerX,center2Y,medium2/2,90,270);

        drawSector(gc, Color.LIGHTGREEN, centerX,center2Y,medium1/2, 90, 270,
                centerX,centerY,big/2,90,270);

        //==================================================================================
        drawSector(gc, Color.LIGHTBLUE, centerX,center2Y,small/2, 270, 355,
                centerX,center2Y,medium2/2,270,357);

        drawSector(gc, Color.LIGHTGREEN, centerX,center2Y,medium1/2, 270, 357,
                centerX,centerY,big/2,270,364);




        //drawSector(gc, Color.LIGHTCYAN, medium1/2, big/2);
    }

    private void drawSector(GraphicsContext gc, Paint p, double x1, double y1, double r1, int angle1, int angle2,
                            double x2, double y2, double r2, int angle3, int angle4){
        Paint tempPaint = gc.getFill();
        gc.setFill(p);

        gc.beginPath();
        double x = getX(x1-r1, y1, x1,y1,angle1),
                y = getY(x1-r1,y1,x1,y1,angle1);

        gc.moveTo(x,y);
        arcTo(gc, x1-r1,y1,x1,y1,angle1,angle2, 1);
        //gc.moveTo(x,y);
        x = getX(x2-r2,y2,x2,y2, angle4);
        y = getY(x2-r2,y2,x2,y2, angle4);
        gc.lineTo(x,y);
        arcTo(gc, x2-r2,y2,x2,y2,angle4,angle3, -1);
        x = getX(x1-r1,y1,x1,y1, angle1);
        y = getY(x1-r1,y1,x1,y1, angle1);
        gc.lineTo(x,y);

        gc.fill();
        gc.stroke();
        gc.closePath();

        gc.setFill(tempPaint);
    }


    private void test(GraphicsContext gc){
        gc.beginPath();


        gc.setStroke(Color.GRAY);
        gc.strokeOval(100,100, 1000,1000);
        gc.strokeRect(100,100,500,500);
        gc.setStroke(Color.BLACK);


        gc.setStroke(Color.RED);
        double x = getX(100,600,600,600,120), y = getY(100,600,600,600,120);
        gc.moveTo(x,y);
        arcTo(gc, 100,600, 600,600, 120, 145, 1);

        gc.stroke();

        gc.closePath();
    }

    private double getX(double x, double y, double x0, double y0, double angle){
        double X = x0 + (x - x0) * Math.cos(Math.toRadians(angle)) - (y - y0) * Math.sin(Math.toRadians(angle));
        return X;
    }

    private double getY(double x, double y, double x0, double y0, double angle){
        double Y = y0 + (y - y0) * Math.cos(Math.toRadians(angle)) + (x - x0) * Math.sin(Math.toRadians(angle));
        return Y;
    }


    private void arcTo(GraphicsContext gc, double x, double y, double x0, double y0, double angleFrom, double angleTo, double step) {
        angleTo = (step < 0) ? angleTo-1:angleTo+1;
        for (double angle = angleFrom; angle!=angleTo; angle+=step) {
            gc.lineTo(getX(x,y,x0,y0,angle), getY(x,y,x0,y0,angle));
        }
    }
}

