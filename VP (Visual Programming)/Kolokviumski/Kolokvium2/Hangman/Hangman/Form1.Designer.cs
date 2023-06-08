namespace Hangman
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
            this.components = new System.ComponentModel.Container();
            this.label1 = new System.Windows.Forms.Label();
            this.lbTimer = new System.Windows.Forms.Label();
            this.lbWord = new System.Windows.Forms.Label();
            this.tbEntryChar = new System.Windows.Forms.TextBox();
            this.btnOK = new System.Windows.Forms.Button();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.lbGuessed = new System.Windows.Forms.Label();
            this.pbError = new System.Windows.Forms.ProgressBar();
            this.pbTimer = new System.Windows.Forms.ProgressBar();
            this.timer1 = new System.Windows.Forms.Timer(this.components);
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(13, 13);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(48, 20);
            this.label1.TabIndex = 0;
            this.label1.Text = "Игра";
            // 
            // lbTimer
            // 
            this.lbTimer.AutoSize = true;
            this.lbTimer.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lbTimer.Location = new System.Drawing.Point(629, 13);
            this.lbTimer.Name = "lbTimer";
            this.lbTimer.Size = new System.Drawing.Size(109, 39);
            this.lbTimer.TabIndex = 1;
            this.lbTimer.Text = "label2";
            // 
            // lbWord
            // 
            this.lbWord.AutoSize = true;
            this.lbWord.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lbWord.Location = new System.Drawing.Point(12, 74);
            this.lbWord.Name = "lbWord";
            this.lbWord.Size = new System.Drawing.Size(109, 39);
            this.lbWord.TabIndex = 2;
            this.lbWord.Text = "label3";
            // 
            // tbEntryChar
            // 
            this.tbEntryChar.Font = new System.Drawing.Font("Microsoft Sans Serif", 30F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.tbEntryChar.Location = new System.Drawing.Point(35, 134);
            this.tbEntryChar.Name = "tbEntryChar";
            this.tbEntryChar.Size = new System.Drawing.Size(100, 64);
            this.tbEntryChar.TabIndex = 3;
            // 
            // btnOK
            // 
            this.btnOK.Font = new System.Drawing.Font("Microsoft Sans Serif", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnOK.Location = new System.Drawing.Point(160, 134);
            this.btnOK.Name = "btnOK";
            this.btnOK.Size = new System.Drawing.Size(89, 64);
            this.btnOK.TabIndex = 4;
            this.btnOK.Text = "OK";
            this.btnOK.UseVisualStyleBackColor = true;
            this.btnOK.Click += new System.EventHandler(this.btnOK_Click);
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.lbGuessed);
            this.groupBox1.Location = new System.Drawing.Point(19, 204);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(730, 100);
            this.groupBox1.TabIndex = 5;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Погодени букви";
            // 
            // lbGuessed
            // 
            this.lbGuessed.AutoSize = true;
            this.lbGuessed.Font = new System.Drawing.Font("Microsoft Sans Serif", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lbGuessed.Location = new System.Drawing.Point(9, 34);
            this.lbGuessed.Name = "lbGuessed";
            this.lbGuessed.Size = new System.Drawing.Size(134, 29);
            this.lbGuessed.TabIndex = 0;
            this.lbGuessed.Text = "lbGuessed";
            // 
            // pbError
            // 
            this.pbError.Location = new System.Drawing.Point(21, 320);
            this.pbError.Name = "pbError";
            this.pbError.Size = new System.Drawing.Size(728, 30);
            this.pbError.TabIndex = 6;
            // 
            // pbTimer
            // 
            this.pbTimer.Location = new System.Drawing.Point(21, 368);
            this.pbTimer.Maximum = 120;
            this.pbTimer.Name = "pbTimer";
            this.pbTimer.Size = new System.Drawing.Size(728, 28);
            this.pbTimer.TabIndex = 7;
            this.pbTimer.Value = 120;
            // 
            // timer1
            // 
            this.timer1.Interval = 1000;
            this.timer1.Tick += new System.EventHandler(this.timer1_Tick);
            // 
            // Form1
            // 
            this.AcceptButton = this.btnOK;
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(762, 450);
            this.Controls.Add(this.pbTimer);
            this.Controls.Add(this.pbError);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.btnOK);
            this.Controls.Add(this.tbEntryChar);
            this.Controls.Add(this.lbWord);
            this.Controls.Add(this.lbTimer);
            this.Controls.Add(this.label1);
            this.Name = "Form1";
            this.Text = "Бесилка";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label lbTimer;
        private System.Windows.Forms.Label lbWord;
        private System.Windows.Forms.TextBox tbEntryChar;
        private System.Windows.Forms.Button btnOK;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Label lbGuessed;
        private System.Windows.Forms.ProgressBar pbError;
        private System.Windows.Forms.ProgressBar pbTimer;
        private System.Windows.Forms.Timer timer1;
    }
}

