using System;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;

namespace Algos4
{
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void btnBuildTree_Click(object sender, RoutedEventArgs e)
        {
            TreeView.Items.Clear();

            TreeViewItem root = new TreeViewItem { Header = "0" };

            TreeViewItem node1 = new TreeViewItem { Header = "1" };
            TreeViewItem node2 = new TreeViewItem { Header = "2" };
            root.Items.Add(node1);
            root.Items.Add(node2);

            TreeViewItem node3 = new TreeViewItem { Header = "3" };
            node1.Items.Add(node3);
            TreeViewItem node6 = new TreeViewItem { Header = "6" };
            node3.Items.Add(node6);
            TreeViewItem node7 = new TreeViewItem { Header = "7" };
            node3.Items.Add(node7);

            node2.Items.Add(new TreeViewItem { Header = "4" });
            node2.Items.Add(new TreeViewItem { Header = "8" });
            node2.Items.Add(new TreeViewItem { Header = "5" });

            AttachCollapsedEvent(root);

            TreeView.Items.Add(root);
        }

        private void AttachCollapsedEvent(TreeViewItem item)
        {
            if (item == null) return;

            item.Collapsed -= TreeViewItemCollapsed;
            item.Collapsed += TreeViewItemCollapsed;

            foreach (object child in item.Items)
            {
                if (child is TreeViewItem childItem)
                {
                    AttachCollapsedEvent(childItem);
                }
            }

            item.Loaded += (s, e) =>
            {
                foreach (object child in item.Items)
                {
                    if (child is TreeViewItem childItem)
                    {
                        AttachCollapsedEvent(childItem);
                    }
                }
            };
        }

        private void TreeViewItemCollapsed(object sender, RoutedEventArgs e)
        {
            if (e.OriginalSource == sender && sender is TreeViewItem item)
            {       
                txtMessages.Text += $"Свернуто: {item.Header}\n";
            }

            e.Handled = true; 
        }

        private void btnShowSelected_Click(object sender, RoutedEventArgs e)
        {
            if (TreeView.SelectedItem is TreeViewItem selectedItem)
            {
                txtMessages.Text += $"Текущий выбранный узел: {selectedItem.Header}\n";
            }
            else
            {
                txtMessages.Text += "Узел не выбран.\n";
            }
        }

        private void btnListChildren_Click(object sender, RoutedEventArgs e)
        {
            if (TreeView.SelectedItem is TreeViewItem selectedItem)
            {
                if (selectedItem.HasItems)
                {
                    txtMessages.Text += $"Дети узла {selectedItem.Header}:\n";
                    foreach (var child in selectedItem.Items)
                    {
                        if (child is TreeViewItem childItem)
                        {
                            txtMessages.Text += $"- {childItem.Header}\n";
                        }
                    }
                }
                else
                {
                    txtMessages.Text += $"Узел {selectedItem.Header} не имеет детей.\n";
                }
            }
            else
            {
                txtMessages.Text += "Узел не выбран.\n";
            }
        }
    }
}