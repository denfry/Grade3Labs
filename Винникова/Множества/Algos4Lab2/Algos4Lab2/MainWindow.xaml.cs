using System;
using System.Windows;
using System.Windows.Controls;

namespace Algos4Lab2
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
            TreeViewItem node3 = new TreeViewItem { Header = "3" };
            root.Items.Add(node1);
            root.Items.Add(node2);
            root.Items.Add(node3);

            TreeViewItem node7 = new TreeViewItem { Header = "7" };
            node3.Items.Add(node7);
            TreeViewItem node6 = new TreeViewItem { Header = "6" };
            node7.Items.Add(node6);
            TreeViewItem node8 = new TreeViewItem { Header = "8" };
            node7.Items.Add(node8);

            node2.Items.Add(new TreeViewItem { Header = "4" });
            node2.Items.Add(new TreeViewItem { Header = "5" });

            AttachEvents(root);

            TreeView.Items.Add(root);
        }

        private void AttachEvents(TreeViewItem item)
        {
            if (item == null) return;

            item.Expanded -= TreeViewItemExpanded;
            item.Expanded += TreeViewItemExpanded;

            foreach (object child in item.Items)
            {
                if (child is TreeViewItem childItem)
                {
                    AttachEvents(childItem);
                }
            }

            item.Loaded += (s, e) =>
            {
                foreach (object child in item.Items)
                {
                    if (child is TreeViewItem childItem)
                    {
                        AttachEvents(childItem);
                    }
                }
            };
        }

        private void TreeViewItemExpanded(object sender, RoutedEventArgs e)
        {
            if (e.OriginalSource == sender && sender is TreeViewItem item)
            {
                txtMessages.Text += $"Развернуто: {item.Header}\n";
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

        

        private void btnShowParent_Click(object sender, RoutedEventArgs e)
        {
            if (TreeView.SelectedItem is TreeViewItem selectedItem)
            {
                TreeViewItem parent = FindParent(TreeView, selectedItem);
                if (parent != null)
                {
                    txtMessages.Text += $"Родитель узла {selectedItem.Header}: {parent.Header}\n";
                }
                else
                {
                    txtMessages.Text += $"Узел {selectedItem.Header} не имеет родителя.\n";
                }
            }
            else
            {
                txtMessages.Text += "Узел не выбран.\n";
            }
        }

        private TreeViewItem FindParent(ItemsControl parent, TreeViewItem child)
        {
            foreach (object item in parent.Items)
            {
                if (item is TreeViewItem treeItem)
                {
                    if (treeItem.Items.Contains(child))
                    {
                        return treeItem;
                    }

                    TreeViewItem found = FindParent(treeItem, child);
                    if (found != null)
                    {
                        return found;
                    }
                }
            }

            return null;
        }
    }
}
