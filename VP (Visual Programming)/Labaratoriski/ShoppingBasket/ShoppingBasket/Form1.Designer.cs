namespace ShoppingBasket
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
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.lbProducts = new System.Windows.Forms.ListBox();
            this.btnEmptyList = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.tbName = new System.Windows.Forms.TextBox();
            this.tbCategory = new System.Windows.Forms.TextBox();
            this.tbPrice = new System.Windows.Forms.TextBox();
            this.btnAddToBasket = new System.Windows.Forms.Button();
            this.btnDeletefromBasket = new System.Windows.Forms.Button();
            this.btnAddNewProduct = new System.Windows.Forms.Button();
            this.btnDeleteProduct = new System.Windows.Forms.Button();
            this.lbBasket = new System.Windows.Forms.ListBox();
            this.lbll = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.tbTotal = new System.Windows.Forms.TextBox();
            this.btnEmptyBasket = new System.Windows.Forms.Button();
            this.nudProductQuantity = new System.Windows.Forms.NumericUpDown();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.nudProductQuantity)).BeginInit();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.btnEmptyList);
            this.groupBox1.Controls.Add(this.lbProducts);
            this.groupBox1.Location = new System.Drawing.Point(23, 13);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(251, 425);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Листа на продукти";
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.tbPrice);
            this.groupBox2.Controls.Add(this.tbCategory);
            this.groupBox2.Controls.Add(this.tbName);
            this.groupBox2.Controls.Add(this.label3);
            this.groupBox2.Controls.Add(this.label2);
            this.groupBox2.Controls.Add(this.label1);
            this.groupBox2.Location = new System.Drawing.Point(293, 13);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(251, 181);
            this.groupBox2.TabIndex = 1;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Детали за продуктот";
            // 
            // lbProducts
            // 
            this.lbProducts.FormattingEnabled = true;
            this.lbProducts.ItemHeight = 16;
            this.lbProducts.Location = new System.Drawing.Point(7, 22);
            this.lbProducts.Name = "lbProducts";
            this.lbProducts.Size = new System.Drawing.Size(238, 356);
            this.lbProducts.TabIndex = 0;
            this.lbProducts.SelectedIndexChanged += new System.EventHandler(this.lbProducts_SelectedIndexChanged);
            // 
            // btnEmptyList
            // 
            this.btnEmptyList.Location = new System.Drawing.Point(6, 396);
            this.btnEmptyList.Name = "btnEmptyList";
            this.btnEmptyList.Size = new System.Drawing.Size(239, 23);
            this.btnEmptyList.TabIndex = 1;
            this.btnEmptyList.Text = "Испразни ја листата?";
            this.btnEmptyList.UseVisualStyleBackColor = true;
            this.btnEmptyList.Click += new System.EventHandler(this.btnEmptyList_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(13, 22);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(37, 16);
            this.label1.TabIndex = 0;
            this.label1.Text = "Име:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(10, 73);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(82, 16);
            this.label2.TabIndex = 1;
            this.label2.Text = "Категорија:";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(13, 118);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(43, 16);
            this.label3.TabIndex = 2;
            this.label3.Text = "Цена:";
            // 
            // tbName
            // 
            this.tbName.Location = new System.Drawing.Point(16, 48);
            this.tbName.Name = "tbName";
            this.tbName.ReadOnly = true;
            this.tbName.Size = new System.Drawing.Size(229, 22);
            this.tbName.TabIndex = 3;
            // 
            // tbCategory
            // 
            this.tbCategory.Location = new System.Drawing.Point(16, 93);
            this.tbCategory.Name = "tbCategory";
            this.tbCategory.ReadOnly = true;
            this.tbCategory.Size = new System.Drawing.Size(229, 22);
            this.tbCategory.TabIndex = 4;
            // 
            // tbPrice
            // 
            this.tbPrice.Location = new System.Drawing.Point(16, 138);
            this.tbPrice.Name = "tbPrice";
            this.tbPrice.ReadOnly = true;
            this.tbPrice.Size = new System.Drawing.Size(229, 22);
            this.tbPrice.TabIndex = 5;
            // 
            // btnAddToBasket
            // 
            this.btnAddToBasket.Location = new System.Drawing.Point(293, 229);
            this.btnAddToBasket.Name = "btnAddToBasket";
            this.btnAddToBasket.Size = new System.Drawing.Size(181, 23);
            this.btnAddToBasket.TabIndex = 2;
            this.btnAddToBasket.Text = "Додади во кошничка >>";
            this.btnAddToBasket.UseVisualStyleBackColor = true;
            this.btnAddToBasket.Click += new System.EventHandler(this.btnAddToBasket_Click);
            // 
            // btnDeletefromBasket
            // 
            this.btnDeletefromBasket.Location = new System.Drawing.Point(293, 279);
            this.btnDeletefromBasket.Name = "btnDeletefromBasket";
            this.btnDeletefromBasket.Size = new System.Drawing.Size(251, 23);
            this.btnDeletefromBasket.TabIndex = 3;
            this.btnDeletefromBasket.Text = "Избриши од кошничка";
            this.btnDeletefromBasket.UseVisualStyleBackColor = true;
            this.btnDeletefromBasket.Click += new System.EventHandler(this.btnDeletefromBasket_Click);
            // 
            // btnAddNewProduct
            // 
            this.btnAddNewProduct.Location = new System.Drawing.Point(293, 332);
            this.btnAddNewProduct.Name = "btnAddNewProduct";
            this.btnAddNewProduct.Size = new System.Drawing.Size(251, 23);
            this.btnAddNewProduct.TabIndex = 4;
            this.btnAddNewProduct.Text = "Додади нов продукт";
            this.btnAddNewProduct.UseVisualStyleBackColor = true;
            this.btnAddNewProduct.Click += new System.EventHandler(this.btnAddNewProduct_Click);
            // 
            // btnDeleteProduct
            // 
            this.btnDeleteProduct.Location = new System.Drawing.Point(293, 382);
            this.btnDeleteProduct.Name = "btnDeleteProduct";
            this.btnDeleteProduct.Size = new System.Drawing.Size(251, 23);
            this.btnDeleteProduct.TabIndex = 5;
            this.btnDeleteProduct.Text = "Избриши продукт";
            this.btnDeleteProduct.UseVisualStyleBackColor = true;
            this.btnDeleteProduct.Click += new System.EventHandler(this.btnDeleteProduct_Click);
            // 
            // lbBasket
            // 
            this.lbBasket.FormattingEnabled = true;
            this.lbBasket.ItemHeight = 16;
            this.lbBasket.Location = new System.Drawing.Point(563, 35);
            this.lbBasket.Name = "lbBasket";
            this.lbBasket.Size = new System.Drawing.Size(238, 324);
            this.lbBasket.TabIndex = 6;
            // 
            // lbll
            // 
            this.lbll.AutoSize = true;
            this.lbll.Location = new System.Drawing.Point(563, 13);
            this.lbll.Name = "lbll";
            this.lbll.Size = new System.Drawing.Size(71, 16);
            this.lbll.TabIndex = 7;
            this.lbll.Text = "Кошничка";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(563, 374);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(58, 16);
            this.label4.TabIndex = 8;
            this.label4.Text = "Вкупно:";
            // 
            // tbTotal
            // 
            this.tbTotal.Location = new System.Drawing.Point(637, 371);
            this.tbTotal.Name = "tbTotal";
            this.tbTotal.ReadOnly = true;
            this.tbTotal.Size = new System.Drawing.Size(164, 22);
            this.tbTotal.TabIndex = 9;
            // 
            // btnEmptyBasket
            // 
            this.btnEmptyBasket.Location = new System.Drawing.Point(563, 408);
            this.btnEmptyBasket.Name = "btnEmptyBasket";
            this.btnEmptyBasket.Size = new System.Drawing.Size(238, 23);
            this.btnEmptyBasket.TabIndex = 10;
            this.btnEmptyBasket.Text = "Испразни ја кошничката?";
            this.btnEmptyBasket.UseVisualStyleBackColor = true;
            this.btnEmptyBasket.Click += new System.EventHandler(this.btnEmptyBasket_Click);
            // 
            // nudProductQuantity
            // 
            this.nudProductQuantity.Location = new System.Drawing.Point(481, 229);
            this.nudProductQuantity.Maximum = new decimal(new int[] {
            500,
            0,
            0,
            0});
            this.nudProductQuantity.Minimum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.nudProductQuantity.Name = "nudProductQuantity";
            this.nudProductQuantity.Size = new System.Drawing.Size(63, 22);
            this.nudProductQuantity.TabIndex = 11;
            this.nudProductQuantity.Value = new decimal(new int[] {
            1,
            0,
            0,
            0});
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(847, 450);
            this.Controls.Add(this.nudProductQuantity);
            this.Controls.Add(this.btnEmptyBasket);
            this.Controls.Add(this.tbTotal);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.lbll);
            this.Controls.Add(this.lbBasket);
            this.Controls.Add(this.btnDeleteProduct);
            this.Controls.Add(this.btnAddNewProduct);
            this.Controls.Add(this.btnDeletefromBasket);
            this.Controls.Add(this.btnAddToBasket);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.groupBox1.ResumeLayout(false);
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.nudProductQuantity)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Button btnEmptyList;
        private System.Windows.Forms.ListBox lbProducts;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.TextBox tbPrice;
        private System.Windows.Forms.TextBox tbCategory;
        private System.Windows.Forms.TextBox tbName;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button btnAddToBasket;
        private System.Windows.Forms.Button btnDeletefromBasket;
        private System.Windows.Forms.Button btnAddNewProduct;
        private System.Windows.Forms.Button btnDeleteProduct;
        private System.Windows.Forms.ListBox lbBasket;
        private System.Windows.Forms.Label lbll;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox tbTotal;
        private System.Windows.Forms.Button btnEmptyBasket;
        private System.Windows.Forms.NumericUpDown nudProductQuantity;
    }
}

