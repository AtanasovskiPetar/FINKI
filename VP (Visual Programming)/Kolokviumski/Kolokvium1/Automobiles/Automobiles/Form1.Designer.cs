namespace Automobiles
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.label9 = new System.Windows.Forms.Label();
            this.label10 = new System.Windows.Forms.Label();
            this.tbModel = new System.Windows.Forms.TextBox();
            this.nudConsumption = new System.Windows.Forms.NumericUpDown();
            this.nudPrice = new System.Windows.Forms.NumericUpDown();
            this.cbBrand = new System.Windows.Forms.ComboBox();
            this.btnAddCar = new System.Windows.Forms.Button();
            this.btnDeleteCar = new System.Windows.Forms.Button();
            this.btnAddBrand = new System.Windows.Forms.Button();
            this.lbBrands = new System.Windows.Forms.ListBox();
            this.lbCars = new System.Windows.Forms.ListBox();
            this.tbAverageConsumption = new System.Windows.Forms.TextBox();
            this.tbMostEconomical = new System.Windows.Forms.TextBox();
            this.tbMostExpensive = new System.Windows.Forms.TextBox();
            ((System.ComponentModel.ISupportInitialize)(this.nudConsumption)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudPrice)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(9, 19);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(108, 16);
            this.label1.TabIndex = 0;
            this.label1.Text = "Нов автомобил";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(27, 46);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(49, 16);
            this.label2.TabIndex = 1;
            this.label2.Text = "Марка";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(27, 92);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(50, 16);
            this.label3.TabIndex = 2;
            this.label3.Text = "Модел";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(27, 136);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(104, 16);
            this.label4.TabIndex = 3;
            this.label4.Text = "Потрошувачка";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(27, 180);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(40, 16);
            this.label5.TabIndex = 4;
            this.label5.Text = "Цена";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(12, 281);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(151, 16);
            this.label6.TabIndex = 5;
            this.label6.Text = "Марки на автомобили";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(415, 19);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(148, 16);
            this.label7.TabIndex = 6;
            this.label7.Text = "Листа на автомобили";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(415, 324);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(170, 16);
            this.label8.TabIndex = 7;
            this.label8.Text = "Просечна потрошувачка";
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(415, 353);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(108, 16);
            this.label9.TabIndex = 8;
            this.label9.Text = "Најекономичен";
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(415, 397);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(58, 16);
            this.label10.TabIndex = 9;
            this.label10.Text = "Најскап";
            // 
            // tbModel
            // 
            this.tbModel.Location = new System.Drawing.Point(30, 111);
            this.tbModel.Name = "tbModel";
            this.tbModel.Size = new System.Drawing.Size(333, 22);
            this.tbModel.TabIndex = 10;
            // 
            // nudConsumption
            // 
            this.nudConsumption.DecimalPlaces = 2;
            this.nudConsumption.Location = new System.Drawing.Point(30, 155);
            this.nudConsumption.Maximum = new decimal(new int[] {
            1000000,
            0,
            0,
            0});
            this.nudConsumption.Name = "nudConsumption";
            this.nudConsumption.Size = new System.Drawing.Size(333, 22);
            this.nudConsumption.TabIndex = 11;
            // 
            // nudPrice
            // 
            this.nudPrice.DecimalPlaces = 2;
            this.nudPrice.Location = new System.Drawing.Point(30, 199);
            this.nudPrice.Maximum = new decimal(new int[] {
            1000000,
            0,
            0,
            0});
            this.nudPrice.Name = "nudPrice";
            this.nudPrice.Size = new System.Drawing.Size(333, 22);
            this.nudPrice.TabIndex = 12;
            // 
            // cbBrand
            // 
            this.cbBrand.FormattingEnabled = true;
            this.cbBrand.Location = new System.Drawing.Point(30, 65);
            this.cbBrand.Name = "cbBrand";
            this.cbBrand.Size = new System.Drawing.Size(333, 24);
            this.cbBrand.TabIndex = 13;
            // 
            // btnAddCar
            // 
            this.btnAddCar.Location = new System.Drawing.Point(30, 239);
            this.btnAddCar.Name = "btnAddCar";
            this.btnAddCar.Size = new System.Drawing.Size(148, 23);
            this.btnAddCar.TabIndex = 14;
            this.btnAddCar.Text = "Додади автомобил";
            this.btnAddCar.UseVisualStyleBackColor = true;
            this.btnAddCar.Click += new System.EventHandler(this.btnAddCar_Click);
            // 
            // btnDeleteCar
            // 
            this.btnDeleteCar.Location = new System.Drawing.Point(197, 239);
            this.btnDeleteCar.Name = "btnDeleteCar";
            this.btnDeleteCar.Size = new System.Drawing.Size(166, 23);
            this.btnDeleteCar.TabIndex = 15;
            this.btnDeleteCar.Text = "Избриши автомобил";
            this.btnDeleteCar.UseVisualStyleBackColor = true;
            this.btnDeleteCar.Click += new System.EventHandler(this.btnDeleteCar_Click);
            // 
            // btnAddBrand
            // 
            this.btnAddBrand.Location = new System.Drawing.Point(175, 415);
            this.btnAddBrand.Name = "btnAddBrand";
            this.btnAddBrand.Size = new System.Drawing.Size(188, 23);
            this.btnAddBrand.TabIndex = 16;
            this.btnAddBrand.Text = "Додади нова марка";
            this.btnAddBrand.UseVisualStyleBackColor = true;
            this.btnAddBrand.Click += new System.EventHandler(this.btnAddBrand_Click);
            // 
            // lbBrands
            // 
            this.lbBrands.FormattingEnabled = true;
            this.lbBrands.ItemHeight = 16;
            this.lbBrands.Location = new System.Drawing.Point(30, 300);
            this.lbBrands.Name = "lbBrands";
            this.lbBrands.Size = new System.Drawing.Size(333, 100);
            this.lbBrands.TabIndex = 17;
            // 
            // lbCars
            // 
            this.lbCars.FormattingEnabled = true;
            this.lbCars.ItemHeight = 16;
            this.lbCars.Location = new System.Drawing.Point(418, 46);
            this.lbCars.Name = "lbCars";
            this.lbCars.Size = new System.Drawing.Size(349, 260);
            this.lbCars.TabIndex = 18;
            // 
            // tbAverageConsumption
            // 
            this.tbAverageConsumption.Location = new System.Drawing.Point(591, 324);
            this.tbAverageConsumption.Name = "tbAverageConsumption";
            this.tbAverageConsumption.ReadOnly = true;
            this.tbAverageConsumption.Size = new System.Drawing.Size(176, 22);
            this.tbAverageConsumption.TabIndex = 19;
            // 
            // tbMostEconomical
            // 
            this.tbMostEconomical.Location = new System.Drawing.Point(418, 372);
            this.tbMostEconomical.Name = "tbMostEconomical";
            this.tbMostEconomical.ReadOnly = true;
            this.tbMostEconomical.Size = new System.Drawing.Size(349, 22);
            this.tbMostEconomical.TabIndex = 20;
            // 
            // tbMostExpensive
            // 
            this.tbMostExpensive.Location = new System.Drawing.Point(418, 415);
            this.tbMostExpensive.Name = "tbMostExpensive";
            this.tbMostExpensive.ReadOnly = true;
            this.tbMostExpensive.Size = new System.Drawing.Size(349, 22);
            this.tbMostExpensive.TabIndex = 21;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(797, 459);
            this.Controls.Add(this.tbMostExpensive);
            this.Controls.Add(this.tbMostEconomical);
            this.Controls.Add(this.tbAverageConsumption);
            this.Controls.Add(this.lbCars);
            this.Controls.Add(this.lbBrands);
            this.Controls.Add(this.btnAddBrand);
            this.Controls.Add(this.btnDeleteCar);
            this.Controls.Add(this.btnAddCar);
            this.Controls.Add(this.cbBrand);
            this.Controls.Add(this.nudPrice);
            this.Controls.Add(this.nudConsumption);
            this.Controls.Add(this.tbModel);
            this.Controls.Add(this.label10);
            this.Controls.Add(this.label9);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "Form1";
            this.Text = "Автомобили";
            ((System.ComponentModel.ISupportInitialize)(this.nudConsumption)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudPrice)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.TextBox tbModel;
        private System.Windows.Forms.NumericUpDown nudConsumption;
        private System.Windows.Forms.NumericUpDown nudPrice;
        private System.Windows.Forms.ComboBox cbBrand;
        private System.Windows.Forms.Button btnAddCar;
        private System.Windows.Forms.Button btnDeleteCar;
        private System.Windows.Forms.Button btnAddBrand;
        private System.Windows.Forms.ListBox lbBrands;
        private System.Windows.Forms.ListBox lbCars;
        private System.Windows.Forms.TextBox tbAverageConsumption;
        private System.Windows.Forms.TextBox tbMostEconomical;
        private System.Windows.Forms.TextBox tbMostExpensive;
    }
}

