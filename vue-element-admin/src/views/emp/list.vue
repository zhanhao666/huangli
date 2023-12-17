<template>
    <div>
        <div style="display:flex;flex-direction: row;justify-content: center;align-items: center;margin-top:20px">
            <el-input v-model="emp.name" placeholder="姓名" style="width:15%;margin-left:10px" ></el-input>
            <el-select v-model="emp.dept" placeholder="部门" style="width:15%;margin-left:10px">
                <el-option
                v-for="item in deptData"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
            </el-select>
            <el-select v-model="emp.education" placeholder="学历" style="width:15%;margin-left:10px">
                <el-option
                v-for="item in educationData"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
            </el-select>
            <el-select v-model="emp.sort" placeholder="排序" style="width:15%;margin-left:10px">
                <el-option
                v-for="item in sortData"
                :key="item.value"
                :label="item.label"
                :value="item.value">
                </el-option>
            </el-select>
            <el-button type="primary" style="margin-left:10px" icon="el-icon-search" @click="getList">搜索</el-button>
            <el-button type="primary" style="margin-left:10px" icon="el-icon-edit" @click="showInsert">添加</el-button>
            <el-button type="primary" style="margin-left:10px" icon="el-icon-download" @click="exportToExcel">导出</el-button>
        </div>
        <br>
        <el-table
        ref="table"
        :data="tableData" border
        style="width: 100%">
            <el-table-column
                sortable
                prop="id"
                label="序号"
                width="180">
            </el-table-column>
            <el-table-column
                prop="empName"
                label="职工姓名"
                width="180">
            </el-table-column>
            <el-table-column
                prop="sex"
                label="性别">
            </el-table-column>
            <el-table-column
                prop="age"
                label="年龄"
                width="180">
            </el-table-column>
            <el-table-column
                prop="deptName"
                label="部门名称"
                width="180">
            </el-table-column>
            <el-table-column
                prop="empDegreeName"
                label="学历">
            </el-table-column>
            <el-table-column
                prop="set"
                label="操作">
                <template slot-scope="scope">
                    <el-button type="primary" style="margin-left:10px"  @click="edit(scope.row)">编辑</el-button>
                    <el-button type="danger" style="margin-left:10px"  @click="deleteEmpById(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div style="display:flex;flex-direction: row;justify-content: center;align-items: center;margin-top:20px">
        <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="emp.currentPage"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="emp.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tableData.length">
        </el-pagination>
        </div>
        <el-dialog title="新增用户" :visible.sync="showDialog" center >
            <el-form :model="insertEmp"  label-width="80px" :rules="rules"  ref="ruleForm">
                <el-form-item label="职工姓名" prop="empName">
                    <el-input v-model="insertEmp.empName" autocomplete="off" style="width:25%"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                    <el-radio v-model="insertEmp.sex" label="男">男</el-radio>
                    <el-radio v-model="insertEmp.sex" label="女">女</el-radio>
                </el-form-item>
                <el-form-item label="年龄" prop="age">
                    <el-input-number v-model="insertEmp.age"></el-input-number>
                </el-form-item>
                <el-form-item label="部门名称" prop="deptName">
                    <el-select v-model="insertEmp.deptName" placeholder="部门" >
                        <el-option
                        v-for="item in deptData"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="学历" prop="empDegreeName">
                    <el-select v-model="insertEmp.empDegreeName" placeholder="学历" >
                        <el-option
                        v-for="item in educationData"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer" style="display:flex;flex-direction: row;justify-content: center;align-items: center">
                <el-button @click="closeInsert('ruleForm')">取 消</el-button>
                <el-button type="primary" @click="insert('ruleForm')">确 定</el-button>
            </div>
        </el-dialog>
        <el-dialog title="修改用户信息" :visible.sync="editable" center >
            <el-form :model="insertEmp"  label-width="80px" :rules="rules"  ref="ruleForm">
                <el-form-item label="职工姓名" prop="empName">
                    <el-input v-model="insertEmp.empName" autocomplete="off" style="width:25%"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                    <el-radio v-model="insertEmp.sex" label="男">男</el-radio>
                    <el-radio v-model="insertEmp.sex" label="女">女</el-radio>
                </el-form-item>
                <el-form-item label="年龄" prop="age">
                    <el-input-number v-model="insertEmp.age"></el-input-number>
                </el-form-item>
                <el-form-item label="部门名称" prop="deptName">
                    <el-select v-model="insertEmp.deptName" placeholder="部门" >
                        <el-option
                        v-for="item in deptData"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="学历" prop="empDegreeName">
                    <el-select v-model="insertEmp.empDegreeName" placeholder="学历" >
                        <el-option
                        v-for="item in educationData"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer" style="display:flex;flex-direction: row;justify-content: center;align-items: center">
                <el-button @click="closeUpdate('ruleForm')">取 消</el-button>
                <el-button type="primary" @click="sureEdit('ruleForm')">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import {getList,insertEmp,deleteEmp,updateEmp} from '@/api/emp';
import XLSX from 'xlsx';
export default {
    data() {
        var checkAge = (rule, value, callback) => {
            if (!value) {
            return callback(new Error('年龄不能为空'));
            }
            setTimeout(() => {
            if (!Number.isInteger(value)) {
                callback(new Error('请输入合法年龄'));
            } else {
                if (value < 0 || value>150) {
                callback(new Error('请输入合法年龄'));
                } else {
                callback();
                }
            }
            }, 1000);
        };
        return {
            editable:false,
            labelPosition:'right',
            showDialog:false,
            tableData:[],
            excelData:[],
            emp:{
                name: '',
                dept:'',
                sort:'',
                education:'',
                currentPage:1,
                pageSize:10
            },
            insertEmp:{
                empName: '',
                age:'0',
                sex:'',
                deptName:'',
                empDegreeName:'',
            }, 
            rules: {
            empName: [
                { required: true, message: '请输入员工姓名', trigger: 'blur' },
            ],
            age: [
                { validator: checkAge, trigger: 'change' }
            ],
            sex: [
                { required: true, message: '请选择性别', trigger: 'change' }
            ],
            deptName: [
                { required: true, message: '请选择员工部门', trigger: 'change' }
            ],
            empDegreeName: [
                { required: true, message: '请选择员工学历', trigger: 'change' }
            ]},         
            educationData:[{
                value: '大专',
                label: '大专'
                }, {
                value: '本科',
                label: '本科'
                }, {
                value: '研究生',
                label: '研究生'
                }
            ],
            deptData: [{
                value: '后勤部',
                label: '后勤部'
                }, {
                value: '业务部',
                label: '业务部'
                }, {
                value: '人事部',
                label: '人事部'
                }
            ],
            sortData: [{
                value: '1',
                label: 'ID升序'
                }, {
                value: '2',
                label: 'ID降序'
                }
            ],
        }
    },
    methods: {
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.getList();
        },
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.getList();
        },
        getList(){
            this.loading = true            
            getList(this.emp)
            .then(res => {
                this.tableData = res.data.records
                this.loading = false
            })
            .catch(() => {
            this.loading = false
            })
        },
        showInsert(){
            this.insertEmp = {};
            this.showDialog = true
        },
        closeInsert(formName){
            this.showDialog = false
        },  
        insert(formName){
            this.$refs[formName].validate((valid) => {
            if (valid) {
                this.loading = true 
                insertEmp(this.insertEmp)
                .then(() => {
                    this.showDialog = false
                    this.$message.success('添加成功')
                    this.loading = false
                })
                .catch(e => {
                    this.getList()
                    this.showDialog = false
                    console.log(e)
                    this.loading = false
                })
            } else {
                console.log('error submit!!');
                return false;
            }
            });
        },
        deleteEmpById(id){
            this.$confirm('此操作将永久删除该员工, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
            }).then(() => {
                this.loading = true 
                deleteEmp(id)
                .then(() => {
                    this.$message.success('删除成功')
                    this.getList()
                    this.loading = false
                })
                .catch(() => {
                this.loading = false
                })
            }).catch(() => {
            this.$message({
                type: 'info',
                message: '已取消删除'
            });          
            });
            
        },
        edit(emp){
            this.insertEmp = emp;
            this.editable = true;
        },
        sureEdit(formName){
            this.$refs[formName].validate((valid) => {
            if (valid) {
                this.loading = true 
                this.editable = true;
            updateEmp(this.insertEmp)
            .then(() => {
                this.getList()
                this.$message.success('修改成功')
                this.editable = false;
                this.loading = false
            })
            .catch(() => {
                this.editable = false;
                this.loading = false
            })
            } else {
                console.log('error submit!!');
                return false;
            }
            });
        },
        closeUpdate(formName){
            this.editable = false;
        },
        exportToExcel(){
            this.excelData = [];
            this.$confirm('此操作将导出所有数据, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
            }).then(() => {
                console.log(this.tableData)
            for(let i = 0;i<this.tableData.length;i++){
                var obj = {
                    序号:this.tableData[i].id,
                    员工姓名:this.tableData[i].empName,
                    性别:this.tableData[i].sex,
                    年龄:this.tableData[i].age,
                    部门:this.tableData[i].deptName,
                    学历:this.tableData[i].empDegreeName
                }
                this.excelData.push(obj);
            };
            const ws = XLSX.utils.json_to_sheet(this.excelData);
            const workSheetName = 'MySheet';
            const workbook = XLSX.utils.book_new();
            XLSX.utils.book_append_sheet(workbook, ws, workSheetName);
            return XLSX.writeFile(workbook, '用户信息.xlsx', { type: 'binary' });
            }).catch(() => {
            this.$message({
                type: 'info',
                message: '已取消导出'
            });          
        }); 
        },
        exportExcelByTable(el, fileName = '用户信息.xlsx'){
            if (!el) {
                throw new Error('没有获取到表格的根 dom 元素');
            }
            const options = { raw: true };
            const workbook = XLSX.utils.table_to_book(el, options);
            return XLSX.writeFile(workbook, fileName, { type: 'binary' });
            }
    },
    mounted() {
        
    },
}
</script>

<style>

</style>