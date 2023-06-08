namespace Busses
{
    partial class NewBus
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
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.btnSave = new System.Windows.Forms.Button();
            this.btnCancel = new System.Windows.Forms.Button();
            this.tbBusName = new System.Windows.Forms.TextBox();
            this.tbBusPlate = new System.Windows.Forms.TextBox();
            this.rbIsLocal = new System.Windows.Forms.RadioButton();
            this.errorProvider1 = new System.Windows.Forms.ErrorProvider(this.components);
            ((System.ComponentModel.ISupportInitialize)(this.errorProvider1)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(31, 22);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(113, 16);
            this.label1.TabIndex = 0;
            this.label1.Text = "Додади автобус";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(52, 49);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(34, 16);
            this.label2.TabIndex = 1;
            this.label2.Text = "Име";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(52, 103);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(95, 16);
            this.label3.TabIndex = 2;
            this.label3.Text = "Регистрација";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(52, 162);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(63, 16);
            this.label4.TabIndex = 3;
            this.label4.Text = "Локален";
            // 
            // btnSave
            // 
            this.btnSave.Location = new System.Drawing.Point(198, 228);
            this.btnSave.Name = "btnSave";
            this.btnSave.Size = new System.Drawing.Size(134, 23);
            this.btnSave.TabIndex = 4;
            this.btnSave.Text = "Зачувај";
            this.btnSave.UseVisualStyleBackColor = true;
            this.btnSave.Click += new System.EventHandler(this.btnSave_Click);
            // 
            // btnCancel
            // 
            this.btnCancel.Location = new System.Drawing.Point(22, 228);
            this.btnCancel.Name = "btnCancel";
            this.btnCancel.Size = new System.Drawing.Size(134, 23);
            this.btnCancel.TabIndex = 5;
            this.btnCancel.Text = "Откажи";
            this.btnCancel.UseVisualStyleBackColor = true;
            this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
            // 
            // tbBusName
            // 
            this.tbBusName.Location = new System.Drawing.Point(55, 68);
            this.tbBusName.Name = "tbBusName";
            this.tbBusName.Size = new System.Drawing.Size(251, 22);
            this.tbBusName.TabIndex = 6;
            this.tbBusName.Validating += new System.ComponentModel.CancelEventHandler(this.tbBusName_Validating);
            // 
            // tbBusPlate
            // 
            this.tbBusPlate.Location = new System.Drawing.Point(55, 122);
            this.tbBusPlate.Name = "tbBusPlate";
            this.tbBusPlate.Size = new System.Drawing.Size(251, 22);
            this.tbBusPlate.TabIndex = 7;
            this.tbBusPlate.Validating += new System.ComponentModel.CancelEventHandler(this.tbBusPlate_Validating);
            // 
            // rbIsLocal
            // 
            this.rbIsLocal.AutoSize = true;
            this.rbIsLocal.Location = new System.Drawing.Point(55, 192);
            this.rbIsLocal.Name = "rbIsLocal";
            this.rbIsLocal.Size = new System.Drawing.Size(137, 20);
            this.rbIsLocal.TabIndex = 8;
            this.rbIsLocal.TabStop = true;
            this.rbIsLocal.Text = "Дали е локален?";
            this.rbIsLocal.UseVisualStyleBackColor = true;
            // 
            // errorProvider1
            // 
            this.errorProvider1.ContainerControl = this;
            // 
            // NewBus
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(363, 298);
            this.Controls.Add(this.rbIsLocal);
            this.Controls.Add(this.tbBusPlate);
            this.Controls.Add(this.tbBusName);
            this.Controls.Add(this.btnCancel);
            this.Controls.Add(this.btnSave);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "NewBus";
            this.Text = "NewBus";
            ((System.ComponentModel.ISupportInitialize)(this.errorProvider1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button btnSave;
        private System.Windows.Forms.Button btnCancel;
        private System.Windows.Forms.TextBox tbBusName;
        private System.Windows.Forms.TextBox tbBusPlate;
        private System.Windows.Forms.RadioButton rbIsLocal;
        private System.Windows.Forms.ErrorProvider errorProvider1;
    }
}