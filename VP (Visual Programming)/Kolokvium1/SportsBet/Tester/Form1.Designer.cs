namespace Tester
{
    partial class Kladilnica
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Kladilnica));
            this.lbTeams = new System.Windows.Forms.ListBox();
            this.lbBilten = new System.Windows.Forms.ListBox();
            this.lbTicket = new System.Windows.Forms.ListBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.btnAdd = new System.Windows.Forms.Button();
            this.backgroundWorker1 = new System.ComponentModel.BackgroundWorker();
            this.nud1 = new System.Windows.Forms.NumericUpDown();
            this.nudX = new System.Windows.Forms.NumericUpDown();
            this.nud2 = new System.Windows.Forms.NumericUpDown();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.btnAddToBilten = new System.Windows.Forms.Button();
            this.tbGameCode = new System.Windows.Forms.TextBox();
            this.label8 = new System.Windows.Forms.Label();
            this.mtbCode = new System.Windows.Forms.MaskedTextBox();
            this.cbType = new System.Windows.Forms.ComboBox();
            this.label9 = new System.Windows.Forms.Label();
            this.backgroundWorker2 = new System.ComponentModel.BackgroundWorker();
            this.btnAddToTicket = new System.Windows.Forms.Button();
            this.tbTotalCoef = new System.Windows.Forms.TextBox();
            this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.label10 = new System.Windows.Forms.Label();
            this.label11 = new System.Windows.Forms.Label();
            this.tbDobivka = new System.Windows.Forms.TextBox();
            this.label12 = new System.Windows.Forms.Label();
            this.nudUplata = new System.Windows.Forms.NumericUpDown();
            ((System.ComponentModel.ISupportInitialize)(this.nud1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudX)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nud2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudUplata)).BeginInit();
            this.SuspendLayout();
            // 
            // lbTeams
            // 
            this.lbTeams.FormattingEnabled = true;
            resources.ApplyResources(this.lbTeams, "lbTeams");
            this.lbTeams.Name = "lbTeams";
            this.lbTeams.SelectionMode = System.Windows.Forms.SelectionMode.MultiExtended;
            this.lbTeams.SelectedIndexChanged += new System.EventHandler(this.lbTeams_SelectedIndexChanged);
            // 
            // lbBilten
            // 
            this.lbBilten.FormattingEnabled = true;
            resources.ApplyResources(this.lbBilten, "lbBilten");
            this.lbBilten.Name = "lbBilten";
            // 
            // lbTicket
            // 
            this.lbTicket.FormattingEnabled = true;
            resources.ApplyResources(this.lbTicket, "lbTicket");
            this.lbTicket.Name = "lbTicket";
            // 
            // label1
            // 
            resources.ApplyResources(this.label1, "label1");
            this.label1.Name = "label1";
            // 
            // label2
            // 
            resources.ApplyResources(this.label2, "label2");
            this.label2.Name = "label2";
            // 
            // label3
            // 
            resources.ApplyResources(this.label3, "label3");
            this.label3.Name = "label3";
            // 
            // btnAdd
            // 
            resources.ApplyResources(this.btnAdd, "btnAdd");
            this.btnAdd.Name = "btnAdd";
            this.btnAdd.UseVisualStyleBackColor = true;
            this.btnAdd.Click += new System.EventHandler(this.button1_Click);
            // 
            // nud1
            // 
            this.nud1.DecimalPlaces = 2;
            resources.ApplyResources(this.nud1, "nud1");
            this.nud1.Maximum = new decimal(new int[] {
            1000,
            0,
            0,
            0});
            this.nud1.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.nud1.Name = "nud1";
            this.nud1.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // nudX
            // 
            this.nudX.DecimalPlaces = 2;
            resources.ApplyResources(this.nudX, "nudX");
            this.nudX.Name = "nudX";
            this.nudX.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // nud2
            // 
            this.nud2.DecimalPlaces = 2;
            resources.ApplyResources(this.nud2, "nud2");
            this.nud2.Name = "nud2";
            this.nud2.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // label4
            // 
            resources.ApplyResources(this.label4, "label4");
            this.label4.Name = "label4";
            // 
            // label5
            // 
            resources.ApplyResources(this.label5, "label5");
            this.label5.Name = "label5";
            // 
            // label6
            // 
            resources.ApplyResources(this.label6, "label6");
            this.label6.Name = "label6";
            // 
            // label7
            // 
            resources.ApplyResources(this.label7, "label7");
            this.label7.Name = "label7";
            // 
            // btnAddToBilten
            // 
            resources.ApplyResources(this.btnAddToBilten, "btnAddToBilten");
            this.btnAddToBilten.Name = "btnAddToBilten";
            this.btnAddToBilten.UseVisualStyleBackColor = true;
            this.btnAddToBilten.Click += new System.EventHandler(this.btnAddToBilten_Click);
            // 
            // tbGameCode
            // 
            resources.ApplyResources(this.tbGameCode, "tbGameCode");
            this.tbGameCode.Name = "tbGameCode";
            this.tbGameCode.TextChanged += new System.EventHandler(this.tbGameCode_TextChanged);
            // 
            // label8
            // 
            resources.ApplyResources(this.label8, "label8");
            this.label8.Name = "label8";
            // 
            // mtbCode
            // 
            resources.ApplyResources(this.mtbCode, "mtbCode");
            this.mtbCode.Name = "mtbCode";
            this.mtbCode.TextMaskFormat = System.Windows.Forms.MaskFormat.IncludePrompt;
            this.mtbCode.MaskInputRejected += new System.Windows.Forms.MaskInputRejectedEventHandler(this.mtbCode_MaskInputRejected);
            // 
            // cbType
            // 
            this.cbType.FormattingEnabled = true;
            resources.ApplyResources(this.cbType, "cbType");
            this.cbType.Name = "cbType";
            this.cbType.Click += new System.EventHandler(this.cbType_Click);
            // 
            // label9
            // 
            resources.ApplyResources(this.label9, "label9");
            this.label9.Name = "label9";
            // 
            // btnAddToTicket
            // 
            resources.ApplyResources(this.btnAddToTicket, "btnAddToTicket");
            this.btnAddToTicket.Name = "btnAddToTicket";
            this.btnAddToTicket.UseVisualStyleBackColor = true;
            this.btnAddToTicket.Click += new System.EventHandler(this.btnAddToTicket_Click);
            // 
            // tbTotalCoef
            // 
            resources.ApplyResources(this.tbTotalCoef, "tbTotalCoef");
            this.tbTotalCoef.Name = "tbTotalCoef";
            this.tbTotalCoef.ReadOnly = true;
            // 
            // contextMenuStrip1
            // 
            this.contextMenuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.contextMenuStrip1.Name = "contextMenuStrip1";
            resources.ApplyResources(this.contextMenuStrip1, "contextMenuStrip1");
            // 
            // label10
            // 
            this.label10.AllowDrop = true;
            resources.ApplyResources(this.label10, "label10");
            this.label10.Name = "label10";
            // 
            // label11
            // 
            resources.ApplyResources(this.label11, "label11");
            this.label11.Name = "label11";
            // 
            // tbDobivka
            // 
            resources.ApplyResources(this.tbDobivka, "tbDobivka");
            this.tbDobivka.Name = "tbDobivka";
            // 
            // label12
            // 
            resources.ApplyResources(this.label12, "label12");
            this.label12.Name = "label12";
            // 
            // nudUplata
            // 
            this.nudUplata.Increment = new decimal(new int[] {
            10,
            0,
            0,
            0});
            resources.ApplyResources(this.nudUplata, "nudUplata");
            this.nudUplata.Maximum = new decimal(new int[] {
            100000,
            0,
            0,
            0});
            this.nudUplata.Minimum = new decimal(new int[] {
            10,
            0,
            0,
            0});
            this.nudUplata.Name = "nudUplata";
            this.nudUplata.Value = new decimal(new int[] {
            50,
            0,
            0,
            0});
            this.nudUplata.ValueChanged += new System.EventHandler(this.nudUplata_ValueChanged);
            // 
            // Kladilnica
            // 
            resources.ApplyResources(this, "$this");
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.nudUplata);
            this.Controls.Add(this.label12);
            this.Controls.Add(this.tbDobivka);
            this.Controls.Add(this.label11);
            this.Controls.Add(this.label10);
            this.Controls.Add(this.tbTotalCoef);
            this.Controls.Add(this.btnAddToTicket);
            this.Controls.Add(this.label9);
            this.Controls.Add(this.cbType);
            this.Controls.Add(this.mtbCode);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.tbGameCode);
            this.Controls.Add(this.btnAddToBilten);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.nud2);
            this.Controls.Add(this.nudX);
            this.Controls.Add(this.nud1);
            this.Controls.Add(this.btnAdd);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.lbTicket);
            this.Controls.Add(this.lbBilten);
            this.Controls.Add(this.lbTeams);
            this.Name = "Kladilnica";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.nud1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudX)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nud2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.nudUplata)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox lbTeams;
        private System.Windows.Forms.ListBox lbBilten;
        private System.Windows.Forms.ListBox lbTicket;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button btnAdd;
        private System.ComponentModel.BackgroundWorker backgroundWorker1;
        private System.Windows.Forms.NumericUpDown nud1;
        private System.Windows.Forms.NumericUpDown nudX;
        private System.Windows.Forms.NumericUpDown nud2;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Button btnAddToBilten;
        private System.Windows.Forms.TextBox tbGameCode;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.MaskedTextBox mtbCode;
        private System.Windows.Forms.ComboBox cbType;
        private System.Windows.Forms.Label label9;
        private System.ComponentModel.BackgroundWorker backgroundWorker2;
        private System.Windows.Forms.Button btnAddToTicket;
        private System.Windows.Forms.TextBox tbTotalCoef;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip1;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.Label label11;
        private System.Windows.Forms.TextBox tbDobivka;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.NumericUpDown nudUplata;
    }
}

