namespace Busses
{
    partial class Busses
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
            this.lbBusses = new System.Windows.Forms.ListBox();
            this.lbLines = new System.Windows.Forms.ListBox();
            this.btnAddBus = new System.Windows.Forms.Button();
            this.btnDeleteBus = new System.Windows.Forms.Button();
            this.btnAddLine = new System.Windows.Forms.Button();
            this.tbMostExpensiveLine = new System.Windows.Forms.TextBox();
            this.tbAverageLinePrice = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(20, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(70, 16);
            this.label1.TabIndex = 0;
            this.label1.Text = "Автобуси";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(391, 9);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(48, 16);
            this.label2.TabIndex = 1;
            this.label2.Text = "Линии";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(400, 306);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(48, 16);
            this.label3.TabIndex = 2;
            this.label3.Text = "Линии";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(421, 328);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(112, 16);
            this.label4.TabIndex = 3;
            this.label4.Text = "Најскапа линија";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(421, 372);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(184, 16);
            this.label5.TabIndex = 4;
            this.label5.Text = "Просечна цена на линиите";
            // 
            // lbBusses
            // 
            this.lbBusses.FormattingEnabled = true;
            this.lbBusses.ItemHeight = 16;
            this.lbBusses.Location = new System.Drawing.Point(23, 32);
            this.lbBusses.Name = "lbBusses";
            this.lbBusses.Size = new System.Drawing.Size(334, 244);
            this.lbBusses.TabIndex = 5;
            this.lbBusses.SelectedIndexChanged += new System.EventHandler(this.lbBusses_SelectedIndexChanged);
            // 
            // lbLines
            // 
            this.lbLines.FormattingEnabled = true;
            this.lbLines.ItemHeight = 16;
            this.lbLines.Location = new System.Drawing.Point(394, 32);
            this.lbLines.Name = "lbLines";
            this.lbLines.Size = new System.Drawing.Size(334, 244);
            this.lbLines.TabIndex = 6;
            // 
            // btnAddBus
            // 
            this.btnAddBus.Location = new System.Drawing.Point(23, 303);
            this.btnAddBus.Name = "btnAddBus";
            this.btnAddBus.Size = new System.Drawing.Size(334, 23);
            this.btnAddBus.TabIndex = 7;
            this.btnAddBus.Text = "Додади автобус";
            this.btnAddBus.UseVisualStyleBackColor = true;
            this.btnAddBus.Click += new System.EventHandler(this.btnAddBus_Click);
            // 
            // btnDeleteBus
            // 
            this.btnDeleteBus.Location = new System.Drawing.Point(23, 346);
            this.btnDeleteBus.Name = "btnDeleteBus";
            this.btnDeleteBus.Size = new System.Drawing.Size(334, 23);
            this.btnDeleteBus.TabIndex = 8;
            this.btnDeleteBus.Text = "Избриши автобус";
            this.btnDeleteBus.UseVisualStyleBackColor = true;
            this.btnDeleteBus.Click += new System.EventHandler(this.btnDeleteBus_Click);
            // 
            // btnAddLine
            // 
            this.btnAddLine.Location = new System.Drawing.Point(23, 390);
            this.btnAddLine.Name = "btnAddLine";
            this.btnAddLine.Size = new System.Drawing.Size(334, 23);
            this.btnAddLine.TabIndex = 9;
            this.btnAddLine.Text = "Додади линија";
            this.btnAddLine.UseVisualStyleBackColor = true;
            this.btnAddLine.Click += new System.EventHandler(this.btnAddLine_Click);
            // 
            // tbMostExpensiveLine
            // 
            this.tbMostExpensiveLine.Location = new System.Drawing.Point(424, 347);
            this.tbMostExpensiveLine.Name = "tbMostExpensiveLine";
            this.tbMostExpensiveLine.ReadOnly = true;
            this.tbMostExpensiveLine.Size = new System.Drawing.Size(289, 22);
            this.tbMostExpensiveLine.TabIndex = 10;
            // 
            // tbAverageLinePrice
            // 
            this.tbAverageLinePrice.Location = new System.Drawing.Point(424, 391);
            this.tbAverageLinePrice.Name = "tbAverageLinePrice";
            this.tbAverageLinePrice.ReadOnly = true;
            this.tbAverageLinePrice.Size = new System.Drawing.Size(289, 22);
            this.tbAverageLinePrice.TabIndex = 11;
            // 
            // Busses
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(740, 450);
            this.Controls.Add(this.tbAverageLinePrice);
            this.Controls.Add(this.tbMostExpensiveLine);
            this.Controls.Add(this.btnAddLine);
            this.Controls.Add(this.btnDeleteBus);
            this.Controls.Add(this.btnAddBus);
            this.Controls.Add(this.lbLines);
            this.Controls.Add(this.lbBusses);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "Busses";
            this.Text = "Busses";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.ListBox lbBusses;
        private System.Windows.Forms.ListBox lbLines;
        private System.Windows.Forms.Button btnAddBus;
        private System.Windows.Forms.Button btnDeleteBus;
        private System.Windows.Forms.Button btnAddLine;
        private System.Windows.Forms.TextBox tbMostExpensiveLine;
        private System.Windows.Forms.TextBox tbAverageLinePrice;
    }
}

