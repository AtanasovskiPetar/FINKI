namespace ExamProblems
{
    partial class ExamProblems
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
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.btnAdd = new System.Windows.Forms.Button();
            this.btnDelete = new System.Windows.Forms.Button();
            this.lbExams = new System.Windows.Forms.ListBox();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.nudPointsProblem2 = new System.Windows.Forms.NumericUpDown();
            this.nudPointsProblem1 = new System.Windows.Forms.NumericUpDown();
            this.btnSaveProblem1 = new System.Windows.Forms.Button();
            this.btnSaveProblem2 = new System.Windows.Forms.Button();
            this.tbDescriptionProblem1 = new System.Windows.Forms.TextBox();
            this.tbDescriptionProblem2 = new System.Windows.Forms.TextBox();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.groupBox3.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.nudPointsProblem2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudPointsProblem1)).BeginInit();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.lbExams);
            this.groupBox1.Controls.Add(this.btnDelete);
            this.groupBox1.Controls.Add(this.btnAdd);
            this.groupBox1.Location = new System.Drawing.Point(12, 12);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(291, 426);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Испити";
            // 
            // btnAdd
            // 
            this.btnAdd.Location = new System.Drawing.Point(6, 357);
            this.btnAdd.Name = "btnAdd";
            this.btnAdd.Size = new System.Drawing.Size(279, 23);
            this.btnAdd.TabIndex = 0;
            this.btnAdd.Text = "Додади";
            this.btnAdd.UseVisualStyleBackColor = true;
            this.btnAdd.Click += new System.EventHandler(this.btnAdd_Click);
            // 
            // btnDelete
            // 
            this.btnDelete.Location = new System.Drawing.Point(7, 397);
            this.btnDelete.Name = "btnDelete";
            this.btnDelete.Size = new System.Drawing.Size(278, 23);
            this.btnDelete.TabIndex = 1;
            this.btnDelete.Text = "Избриши";
            this.btnDelete.UseVisualStyleBackColor = true;
            this.btnDelete.Click += new System.EventHandler(this.btnDelete_Click);
            // 
            // lbExams
            // 
            this.lbExams.FormattingEnabled = true;
            this.lbExams.ItemHeight = 16;
            this.lbExams.Location = new System.Drawing.Point(7, 22);
            this.lbExams.Name = "lbExams";
            this.lbExams.Size = new System.Drawing.Size(278, 324);
            this.lbExams.TabIndex = 2;
            this.lbExams.SelectedIndexChanged += new System.EventHandler(this.lbExams_SelectedIndexChanged);
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.tbDescriptionProblem1);
            this.groupBox2.Controls.Add(this.btnSaveProblem1);
            this.groupBox2.Controls.Add(this.nudPointsProblem1);
            this.groupBox2.Controls.Add(this.label2);
            this.groupBox2.Controls.Add(this.label1);
            this.groupBox2.Location = new System.Drawing.Point(333, 12);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(285, 212);
            this.groupBox2.TabIndex = 1;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Задача 1";
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.tbDescriptionProblem2);
            this.groupBox3.Controls.Add(this.btnSaveProblem2);
            this.groupBox3.Controls.Add(this.nudPointsProblem2);
            this.groupBox3.Controls.Add(this.label4);
            this.groupBox3.Controls.Add(this.label3);
            this.groupBox3.Location = new System.Drawing.Point(333, 230);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(285, 212);
            this.groupBox3.TabIndex = 2;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "Задача 2";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(7, 22);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(40, 16);
            this.label1.TabIndex = 0;
            this.label1.Text = "Опис";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(7, 133);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(49, 16);
            this.label2.TabIndex = 1;
            this.label2.Text = "Поени";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(7, 18);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(40, 16);
            this.label3.TabIndex = 0;
            this.label3.Text = "Опис";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(7, 139);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(49, 16);
            this.label4.TabIndex = 1;
            this.label4.Text = "Поени";
            // 
            // nudPointsProblem2
            // 
            this.nudPointsProblem2.Location = new System.Drawing.Point(10, 159);
            this.nudPointsProblem2.Name = "nudPointsProblem2";
            this.nudPointsProblem2.Size = new System.Drawing.Size(155, 22);
            this.nudPointsProblem2.TabIndex = 3;
            // 
            // nudPointsProblem1
            // 
            this.nudPointsProblem1.Location = new System.Drawing.Point(6, 152);
            this.nudPointsProblem1.Name = "nudPointsProblem1";
            this.nudPointsProblem1.Size = new System.Drawing.Size(155, 22);
            this.nudPointsProblem1.TabIndex = 3;
            // 
            // btnSaveProblem1
            // 
            this.btnSaveProblem1.Location = new System.Drawing.Point(188, 183);
            this.btnSaveProblem1.Name = "btnSaveProblem1";
            this.btnSaveProblem1.Size = new System.Drawing.Size(75, 23);
            this.btnSaveProblem1.TabIndex = 4;
            this.btnSaveProblem1.Text = "Зачувај";
            this.btnSaveProblem1.UseVisualStyleBackColor = true;
            this.btnSaveProblem1.Click += new System.EventHandler(this.btnSaveProblem1_Click);
            // 
            // btnSaveProblem2
            // 
            this.btnSaveProblem2.Location = new System.Drawing.Point(188, 183);
            this.btnSaveProblem2.Name = "btnSaveProblem2";
            this.btnSaveProblem2.Size = new System.Drawing.Size(75, 23);
            this.btnSaveProblem2.TabIndex = 4;
            this.btnSaveProblem2.Text = "Зачувај";
            this.btnSaveProblem2.UseVisualStyleBackColor = true;
            this.btnSaveProblem2.Click += new System.EventHandler(this.btnSaveProblem2_Click);
            // 
            // tbDescriptionProblem1
            // 
            this.tbDescriptionProblem1.Location = new System.Drawing.Point(6, 41);
            this.tbDescriptionProblem1.Multiline = true;
            this.tbDescriptionProblem1.Name = "tbDescriptionProblem1";
            this.tbDescriptionProblem1.Size = new System.Drawing.Size(269, 89);
            this.tbDescriptionProblem1.TabIndex = 5;
            // 
            // tbDescriptionProblem2
            // 
            this.tbDescriptionProblem2.Location = new System.Drawing.Point(10, 38);
            this.tbDescriptionProblem2.Multiline = true;
            this.tbDescriptionProblem2.Name = "tbDescriptionProblem2";
            this.tbDescriptionProblem2.Size = new System.Drawing.Size(265, 90);
            this.tbDescriptionProblem2.TabIndex = 5;
            // 
            // ExamProblems
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(638, 450);
            this.Controls.Add(this.groupBox3);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Name = "ExamProblems";
            this.Text = "Form1";
            this.groupBox1.ResumeLayout(false);
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            this.groupBox3.ResumeLayout(false);
            this.groupBox3.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.nudPointsProblem2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudPointsProblem1)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.ListBox lbExams;
        private System.Windows.Forms.Button btnDelete;
        private System.Windows.Forms.Button btnAdd;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.Button btnSaveProblem1;
        private System.Windows.Forms.NumericUpDown nudPointsProblem1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.Button btnSaveProblem2;
        private System.Windows.Forms.NumericUpDown nudPointsProblem2;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox tbDescriptionProblem1;
        private System.Windows.Forms.TextBox tbDescriptionProblem2;
    }
}

