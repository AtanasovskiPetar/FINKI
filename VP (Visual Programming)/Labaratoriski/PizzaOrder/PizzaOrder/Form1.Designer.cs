namespace PizzaOrder
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
            this.rbSmall = new System.Windows.Forms.RadioButton();
            this.rbMedium = new System.Windows.Forms.RadioButton();
            this.rbLarge = new System.Windows.Forms.RadioButton();
            this.tbSmall = new System.Windows.Forms.TextBox();
            this.tbMedium = new System.Windows.Forms.TextBox();
            this.tbLarge = new System.Windows.Forms.TextBox();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.tbTotalBeerPrice = new System.Windows.Forms.TextBox();
            this.tbTotalJuicePrice = new System.Windows.Forms.TextBox();
            this.tbTotalCocaPrice = new System.Windows.Forms.TextBox();
            this.tbBeerPrice = new System.Windows.Forms.TextBox();
            this.tbJuicePrice = new System.Windows.Forms.TextBox();
            this.tbColaPrice = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.tbBeerQuantity = new System.Windows.Forms.TextBox();
            this.tbJuiceQuantity = new System.Windows.Forms.TextBox();
            this.tbCocaQuantity = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.btnCancel = new System.Windows.Forms.Button();
            this.btnOrder = new System.Windows.Forms.Button();
            this.tbDeserPrice = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.lbDeserts = new System.Windows.Forms.ListBox();
            this.groupBox4 = new System.Windows.Forms.GroupBox();
            this.tbKetchup = new System.Windows.Forms.TextBox();
            this.tbExtraCheese = new System.Windows.Forms.TextBox();
            this.tbFeferonki = new System.Windows.Forms.TextBox();
            this.checkBox3 = new System.Windows.Forms.CheckBox();
            this.checkBox2 = new System.Windows.Forms.CheckBox();
            this.checkBox1 = new System.Windows.Forms.CheckBox();
            this.groupBox5 = new System.Windows.Forms.GroupBox();
            this.tbChange = new System.Windows.Forms.TextBox();
            this.tbGiven = new System.Windows.Forms.TextBox();
            this.tbToPayTotal = new System.Windows.Forms.TextBox();
            this.label9 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.lal = new System.Windows.Forms.Label();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.groupBox3.SuspendLayout();
            this.groupBox4.SuspendLayout();
            this.groupBox5.SuspendLayout();
            this.SuspendLayout();
            // 
            // rbSmall
            // 
            this.rbSmall.AutoSize = true;
            this.rbSmall.Location = new System.Drawing.Point(6, 34);
            this.rbSmall.Name = "rbSmall";
            this.rbSmall.Size = new System.Drawing.Size(63, 20);
            this.rbSmall.TabIndex = 1;
            this.rbSmall.TabStop = true;
            this.rbSmall.Text = "Мала";
            this.rbSmall.UseVisualStyleBackColor = true;
            this.rbSmall.CheckedChanged += new System.EventHandler(this.rbSmall_CheckedChanged);
            // 
            // rbMedium
            // 
            this.rbMedium.AutoSize = true;
            this.rbMedium.Location = new System.Drawing.Point(6, 71);
            this.rbMedium.Name = "rbMedium";
            this.rbMedium.Size = new System.Drawing.Size(77, 20);
            this.rbMedium.TabIndex = 2;
            this.rbMedium.TabStop = true;
            this.rbMedium.Text = "Средна";
            this.rbMedium.UseVisualStyleBackColor = true;
            this.rbMedium.CheckedChanged += new System.EventHandler(this.rbMedium_CheckedChanged);
            // 
            // rbLarge
            // 
            this.rbLarge.AutoSize = true;
            this.rbLarge.Location = new System.Drawing.Point(7, 111);
            this.rbLarge.Name = "rbLarge";
            this.rbLarge.Size = new System.Drawing.Size(76, 20);
            this.rbLarge.TabIndex = 3;
            this.rbLarge.TabStop = true;
            this.rbLarge.Text = "Голема";
            this.rbLarge.UseVisualStyleBackColor = true;
            this.rbLarge.CheckedChanged += new System.EventHandler(this.rbLarge_CheckedChanged);
            // 
            // tbSmall
            // 
            this.tbSmall.Location = new System.Drawing.Point(209, 34);
            this.tbSmall.Name = "tbSmall";
            this.tbSmall.Size = new System.Drawing.Size(127, 22);
            this.tbSmall.TabIndex = 4;
            this.tbSmall.Text = "200";
            this.tbSmall.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.tbSmall.TextChanged += new System.EventHandler(this.tbSmall_TextChanged);
            // 
            // tbMedium
            // 
            this.tbMedium.Location = new System.Drawing.Point(209, 71);
            this.tbMedium.Name = "tbMedium";
            this.tbMedium.Size = new System.Drawing.Size(127, 22);
            this.tbMedium.TabIndex = 5;
            this.tbMedium.Text = "300";
            this.tbMedium.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.tbMedium.TextChanged += new System.EventHandler(this.tbSmall_TextChanged);
            // 
            // tbLarge
            // 
            this.tbLarge.Location = new System.Drawing.Point(209, 109);
            this.tbLarge.Name = "tbLarge";
            this.tbLarge.Size = new System.Drawing.Size(127, 22);
            this.tbLarge.TabIndex = 6;
            this.tbLarge.Text = "500";
            this.tbLarge.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.tbLarge.TextChanged += new System.EventHandler(this.tbSmall_TextChanged);
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.rbSmall);
            this.groupBox1.Controls.Add(this.tbLarge);
            this.groupBox1.Controls.Add(this.rbMedium);
            this.groupBox1.Controls.Add(this.tbMedium);
            this.groupBox1.Controls.Add(this.rbLarge);
            this.groupBox1.Controls.Add(this.tbSmall);
            this.groupBox1.Location = new System.Drawing.Point(21, 12);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(358, 167);
            this.groupBox1.TabIndex = 7;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Големина";
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.tbTotalBeerPrice);
            this.groupBox2.Controls.Add(this.tbTotalJuicePrice);
            this.groupBox2.Controls.Add(this.tbTotalCocaPrice);
            this.groupBox2.Controls.Add(this.tbBeerPrice);
            this.groupBox2.Controls.Add(this.tbJuicePrice);
            this.groupBox2.Controls.Add(this.tbColaPrice);
            this.groupBox2.Controls.Add(this.label7);
            this.groupBox2.Controls.Add(this.label6);
            this.groupBox2.Controls.Add(this.tbBeerQuantity);
            this.groupBox2.Controls.Add(this.tbJuiceQuantity);
            this.groupBox2.Controls.Add(this.tbCocaQuantity);
            this.groupBox2.Controls.Add(this.label4);
            this.groupBox2.Controls.Add(this.label3);
            this.groupBox2.Controls.Add(this.label2);
            this.groupBox2.Controls.Add(this.label1);
            this.groupBox2.Location = new System.Drawing.Point(21, 185);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(737, 167);
            this.groupBox2.TabIndex = 8;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Пијалок";
            // 
            // tbTotalBeerPrice
            // 
            this.tbTotalBeerPrice.Location = new System.Drawing.Point(592, 111);
            this.tbTotalBeerPrice.Name = "tbTotalBeerPrice";
            this.tbTotalBeerPrice.ReadOnly = true;
            this.tbTotalBeerPrice.Size = new System.Drawing.Size(127, 22);
            this.tbTotalBeerPrice.TabIndex = 14;
            // 
            // tbTotalJuicePrice
            // 
            this.tbTotalJuicePrice.Location = new System.Drawing.Point(592, 76);
            this.tbTotalJuicePrice.Name = "tbTotalJuicePrice";
            this.tbTotalJuicePrice.ReadOnly = true;
            this.tbTotalJuicePrice.Size = new System.Drawing.Size(127, 22);
            this.tbTotalJuicePrice.TabIndex = 13;
            // 
            // tbTotalCocaPrice
            // 
            this.tbTotalCocaPrice.Location = new System.Drawing.Point(592, 39);
            this.tbTotalCocaPrice.Name = "tbTotalCocaPrice";
            this.tbTotalCocaPrice.ReadOnly = true;
            this.tbTotalCocaPrice.Size = new System.Drawing.Size(127, 22);
            this.tbTotalCocaPrice.TabIndex = 12;
            // 
            // tbBeerPrice
            // 
            this.tbBeerPrice.Location = new System.Drawing.Point(398, 112);
            this.tbBeerPrice.Name = "tbBeerPrice";
            this.tbBeerPrice.Size = new System.Drawing.Size(130, 22);
            this.tbBeerPrice.TabIndex = 11;
            this.tbBeerPrice.Text = "80";
            this.tbBeerPrice.TextChanged += new System.EventHandler(this.tbBeerPrice_TextChanged);
            // 
            // tbJuicePrice
            // 
            this.tbJuicePrice.Location = new System.Drawing.Point(398, 76);
            this.tbJuicePrice.Name = "tbJuicePrice";
            this.tbJuicePrice.Size = new System.Drawing.Size(130, 22);
            this.tbJuicePrice.TabIndex = 10;
            this.tbJuicePrice.Text = "70";
            this.tbJuicePrice.TextChanged += new System.EventHandler(this.tbJuicePrice_TextChanged);
            // 
            // tbColaPrice
            // 
            this.tbColaPrice.Location = new System.Drawing.Point(398, 39);
            this.tbColaPrice.Name = "tbColaPrice";
            this.tbColaPrice.Size = new System.Drawing.Size(130, 22);
            this.tbColaPrice.TabIndex = 9;
            this.tbColaPrice.Text = "60";
            this.tbColaPrice.TextChanged += new System.EventHandler(this.tbColaPrice_TextChanged);
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(631, 10);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(55, 16);
            this.label7.TabIndex = 8;
            this.label7.Text = "Вкупно";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(431, 10);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(40, 16);
            this.label6.TabIndex = 7;
            this.label6.Text = "Цена";
            // 
            // tbBeerQuantity
            // 
            this.tbBeerQuantity.Location = new System.Drawing.Point(209, 112);
            this.tbBeerQuantity.Name = "tbBeerQuantity";
            this.tbBeerQuantity.Size = new System.Drawing.Size(127, 22);
            this.tbBeerQuantity.TabIndex = 6;
            this.tbBeerQuantity.TextChanged += new System.EventHandler(this.tbBeerQuantity_TextChanged);
            // 
            // tbJuiceQuantity
            // 
            this.tbJuiceQuantity.Location = new System.Drawing.Point(209, 76);
            this.tbJuiceQuantity.Name = "tbJuiceQuantity";
            this.tbJuiceQuantity.Size = new System.Drawing.Size(127, 22);
            this.tbJuiceQuantity.TabIndex = 5;
            this.tbJuiceQuantity.TextChanged += new System.EventHandler(this.tbJuiceQuantity_TextChanged);
            // 
            // tbCocaQuantity
            // 
            this.tbCocaQuantity.Location = new System.Drawing.Point(209, 42);
            this.tbCocaQuantity.Name = "tbCocaQuantity";
            this.tbCocaQuantity.Size = new System.Drawing.Size(127, 22);
            this.tbCocaQuantity.TabIndex = 4;
            this.tbCocaQuantity.TextChanged += new System.EventHandler(this.tbCocaQuantity_TextChanged_1);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(244, 10);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(71, 16);
            this.label4.TabIndex = 3;
            this.label4.Text = "Количина";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(11, 111);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(41, 16);
            this.label3.TabIndex = 2;
            this.label3.Text = "Пиво";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(11, 76);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(175, 16);
            this.label2.TabIndex = 1;
            this.label2.Text = "Сок од јаболко / портокал";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(11, 45);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(177, 16);
            this.label1.TabIndex = 0;
            this.label1.Text = "Кока кола / Фанта / Спрајт";
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.btnCancel);
            this.groupBox3.Controls.Add(this.btnOrder);
            this.groupBox3.Controls.Add(this.tbDeserPrice);
            this.groupBox3.Controls.Add(this.label5);
            this.groupBox3.Controls.Add(this.lbDeserts);
            this.groupBox3.Location = new System.Drawing.Point(21, 358);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(358, 167);
            this.groupBox3.TabIndex = 9;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "Десерт";
            // 
            // btnCancel
            // 
            this.btnCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.btnCancel.Location = new System.Drawing.Point(186, 120);
            this.btnCancel.Name = "btnCancel";
            this.btnCancel.Size = new System.Drawing.Size(150, 23);
            this.btnCancel.TabIndex = 4;
            this.btnCancel.Text = "Откажи";
            this.btnCancel.UseVisualStyleBackColor = true;
            // 
            // btnOrder
            // 
            this.btnOrder.Location = new System.Drawing.Point(186, 78);
            this.btnOrder.Name = "btnOrder";
            this.btnOrder.Size = new System.Drawing.Size(150, 23);
            this.btnOrder.TabIndex = 3;
            this.btnOrder.Text = "Нарачај";
            this.btnOrder.UseVisualStyleBackColor = true;
            this.btnOrder.Click += new System.EventHandler(this.btnOrder_Click);
            // 
            // tbDeserPrice
            // 
            this.tbDeserPrice.Location = new System.Drawing.Point(186, 40);
            this.tbDeserPrice.Name = "tbDeserPrice";
            this.tbDeserPrice.Size = new System.Drawing.Size(150, 22);
            this.tbDeserPrice.TabIndex = 2;
            this.tbDeserPrice.TextChanged += new System.EventHandler(this.tbDeserPrice_TextChanged);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(183, 18);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(111, 16);
            this.label5.TabIndex = 1;
            this.label5.Text = "Цена на десерт:";
            // 
            // lbDeserts
            // 
            this.lbDeserts.FormattingEnabled = true;
            this.lbDeserts.ItemHeight = 16;
            this.lbDeserts.Items.AddRange(new object[] {
            "Овошна пита",
            "Сладолед",
            "Торта"});
            this.lbDeserts.Location = new System.Drawing.Point(14, 22);
            this.lbDeserts.Name = "lbDeserts";
            this.lbDeserts.SelectionMode = System.Windows.Forms.SelectionMode.MultiSimple;
            this.lbDeserts.Size = new System.Drawing.Size(154, 132);
            this.lbDeserts.TabIndex = 0;
            this.lbDeserts.SelectedIndexChanged += new System.EventHandler(this.lbDeserts_SelectedIndexChanged);
            // 
            // groupBox4
            // 
            this.groupBox4.Controls.Add(this.tbKetchup);
            this.groupBox4.Controls.Add(this.tbExtraCheese);
            this.groupBox4.Controls.Add(this.tbFeferonki);
            this.groupBox4.Controls.Add(this.checkBox3);
            this.groupBox4.Controls.Add(this.checkBox2);
            this.groupBox4.Controls.Add(this.checkBox1);
            this.groupBox4.Location = new System.Drawing.Point(400, 12);
            this.groupBox4.Name = "groupBox4";
            this.groupBox4.Size = new System.Drawing.Size(358, 167);
            this.groupBox4.TabIndex = 10;
            this.groupBox4.TabStop = false;
            this.groupBox4.Text = "Додатоци";
            // 
            // tbKetchup
            // 
            this.tbKetchup.Location = new System.Drawing.Point(213, 107);
            this.tbKetchup.Name = "tbKetchup";
            this.tbKetchup.Size = new System.Drawing.Size(127, 22);
            this.tbKetchup.TabIndex = 5;
            this.tbKetchup.Text = "20";
            // 
            // tbExtraCheese
            // 
            this.tbExtraCheese.Location = new System.Drawing.Point(213, 68);
            this.tbExtraCheese.Name = "tbExtraCheese";
            this.tbExtraCheese.Size = new System.Drawing.Size(127, 22);
            this.tbExtraCheese.TabIndex = 4;
            this.tbExtraCheese.Text = "30";
            // 
            // tbFeferonki
            // 
            this.tbFeferonki.Location = new System.Drawing.Point(213, 34);
            this.tbFeferonki.Name = "tbFeferonki";
            this.tbFeferonki.Size = new System.Drawing.Size(127, 22);
            this.tbFeferonki.TabIndex = 3;
            this.tbFeferonki.Text = "40";
            // 
            // checkBox3
            // 
            this.checkBox3.AutoSize = true;
            this.checkBox3.Location = new System.Drawing.Point(19, 109);
            this.checkBox3.Name = "checkBox3";
            this.checkBox3.Size = new System.Drawing.Size(69, 20);
            this.checkBox3.TabIndex = 2;
            this.checkBox3.Text = "Кечап";
            this.checkBox3.UseVisualStyleBackColor = true;
            this.checkBox3.CheckedChanged += new System.EventHandler(this.checkBox3_CheckedChanged);
            // 
            // checkBox2
            // 
            this.checkBox2.AutoSize = true;
            this.checkBox2.Location = new System.Drawing.Point(19, 70);
            this.checkBox2.Name = "checkBox2";
            this.checkBox2.Size = new System.Drawing.Size(130, 20);
            this.checkBox2.TabIndex = 1;
            this.checkBox2.Text = "Екстра сирење";
            this.checkBox2.UseVisualStyleBackColor = true;
            this.checkBox2.CheckedChanged += new System.EventHandler(this.checkBox2_CheckedChanged);
            // 
            // checkBox1
            // 
            this.checkBox1.AutoSize = true;
            this.checkBox1.Location = new System.Drawing.Point(19, 33);
            this.checkBox1.Name = "checkBox1";
            this.checkBox1.Size = new System.Drawing.Size(106, 20);
            this.checkBox1.TabIndex = 0;
            this.checkBox1.Text = "Феферонки";
            this.checkBox1.UseVisualStyleBackColor = true;
            this.checkBox1.CheckedChanged += new System.EventHandler(this.checkBox1_CheckedChanged);
            // 
            // groupBox5
            // 
            this.groupBox5.Controls.Add(this.tbChange);
            this.groupBox5.Controls.Add(this.tbGiven);
            this.groupBox5.Controls.Add(this.tbToPayTotal);
            this.groupBox5.Controls.Add(this.label9);
            this.groupBox5.Controls.Add(this.label8);
            this.groupBox5.Controls.Add(this.lal);
            this.groupBox5.Location = new System.Drawing.Point(400, 358);
            this.groupBox5.Name = "groupBox5";
            this.groupBox5.Size = new System.Drawing.Size(358, 167);
            this.groupBox5.TabIndex = 11;
            this.groupBox5.TabStop = false;
            this.groupBox5.Text = "Наплата";
            // 
            // tbChange
            // 
            this.tbChange.Location = new System.Drawing.Point(182, 117);
            this.tbChange.Name = "tbChange";
            this.tbChange.Size = new System.Drawing.Size(158, 22);
            this.tbChange.TabIndex = 5;
            // 
            // tbGiven
            // 
            this.tbGiven.Location = new System.Drawing.Point(182, 78);
            this.tbGiven.Name = "tbGiven";
            this.tbGiven.Size = new System.Drawing.Size(158, 22);
            this.tbGiven.TabIndex = 4;
            this.tbGiven.TextChanged += new System.EventHandler(this.tbGiven_TextChanged);
            // 
            // tbToPayTotal
            // 
            this.tbToPayTotal.Location = new System.Drawing.Point(182, 33);
            this.tbToPayTotal.Name = "tbToPayTotal";
            this.tbToPayTotal.ReadOnly = true;
            this.tbToPayTotal.Size = new System.Drawing.Size(158, 22);
            this.tbToPayTotal.TabIndex = 3;
            this.tbToPayTotal.TextChanged += new System.EventHandler(this.tbToPayTotal_TextChanged);
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(19, 120);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(85, 16);
            this.label9.TabIndex = 2;
            this.label9.Text = "За враќање";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(19, 78);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(80, 16);
            this.label8.TabIndex = 1;
            this.label8.Text = "Наплатено";
            // 
            // lal
            // 
            this.lal.AutoSize = true;
            this.lal.Location = new System.Drawing.Point(19, 40);
            this.lal.Name = "lal";
            this.lal.Size = new System.Drawing.Size(135, 16);
            this.lal.TabIndex = 0;
            this.lal.Text = "Вкупно за плаќање";
            // 
            // Form1
            // 
            this.AcceptButton = this.btnOrder;
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.CancelButton = this.btnCancel;
            this.ClientSize = new System.Drawing.Size(800, 558);
            this.Controls.Add(this.groupBox5);
            this.Controls.Add(this.groupBox4);
            this.Controls.Add(this.groupBox3);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            this.groupBox3.ResumeLayout(false);
            this.groupBox3.PerformLayout();
            this.groupBox4.ResumeLayout(false);
            this.groupBox4.PerformLayout();
            this.groupBox5.ResumeLayout(false);
            this.groupBox5.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.RadioButton rbSmall;
        private System.Windows.Forms.RadioButton rbMedium;
        private System.Windows.Forms.RadioButton rbLarge;
        private System.Windows.Forms.TextBox tbSmall;
        private System.Windows.Forms.TextBox tbMedium;
        private System.Windows.Forms.TextBox tbLarge;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.TextBox tbBeerQuantity;
        private System.Windows.Forms.TextBox tbJuiceQuantity;
        private System.Windows.Forms.TextBox tbCocaQuantity;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.Button btnCancel;
        private System.Windows.Forms.Button btnOrder;
        private System.Windows.Forms.TextBox tbDeserPrice;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.ListBox lbDeserts;
        private System.Windows.Forms.TextBox tbTotalJuicePrice;
        private System.Windows.Forms.TextBox tbTotalCocaPrice;
        private System.Windows.Forms.TextBox tbBeerPrice;
        private System.Windows.Forms.TextBox tbJuicePrice;
        private System.Windows.Forms.TextBox tbColaPrice;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.GroupBox groupBox4;
        private System.Windows.Forms.TextBox tbKetchup;
        private System.Windows.Forms.TextBox tbExtraCheese;
        private System.Windows.Forms.TextBox tbFeferonki;
        private System.Windows.Forms.CheckBox checkBox3;
        private System.Windows.Forms.CheckBox checkBox2;
        private System.Windows.Forms.CheckBox checkBox1;
        private System.Windows.Forms.GroupBox groupBox5;
        private System.Windows.Forms.TextBox tbChange;
        private System.Windows.Forms.TextBox tbGiven;
        private System.Windows.Forms.TextBox tbToPayTotal;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label lal;
        private System.Windows.Forms.TextBox tbTotalBeerPrice;
    }
}

