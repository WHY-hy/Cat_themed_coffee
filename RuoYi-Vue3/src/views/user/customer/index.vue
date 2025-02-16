<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户账号" prop="customerName">
        <el-input v-model="queryParams.customerName" placeholder="请输入用户账号" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="用户昵称" prop="custname">
        <el-input v-model="queryParams.custname" placeholder="请输入用户昵称" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="电话" prop="phonenumber">
        <el-input v-model="queryParams.phonenumber" placeholder="请输入电话" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable v-model="queryParams.createTime" type="date" value-format="YYYY-MM-DD"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['user:customer:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['user:customer:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['user:customer:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['user:customer:export']">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="customerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="头像" align="center" key="avatar" width="">
        <template #default="scope">
          <el-avatar :size="40" :src="getAvatarUrl(scope.row.avatar)" @error="handleAvatarError">
            <img src="@/assets/images/profile.jpg" />
          </el-avatar>
        </template>
      </el-table-column>
      <el-table-column label="用户账号" align="center" prop="customerName" />
      <el-table-column label="用户昵称" align="center" prop="custname" />
      <el-table-column label="邮箱" align="center" prop="email" />
      <el-table-column label="电话" align="center" prop="phonenumber" />
       <el-table-column label="状态" align="center" prop="accountStatus">
        <template #default="scope">
          <dict-tag :options="sys_normal_disable" :value="scope.row.status" />
        </template>
      </el-table-column>
    <!--   <el-table-column label="状态" align="center" key="status" v-if="columns[5].visible">
        <template #default="scope">
          <el-switch v-model="scope.row.status" active-value="0" inactive-value="1"
            @change="handleStatusChange(scope.row)"></el-switch>
        </template>
      </el-table-column> -->
      <el-table-column label="余额" align="center" prop="balance" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-tooltip content="修改" placement="top" v-if="scope.row.userId !== 1">
            <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
              v-hasPermi="['system:user:edit']"></el-button>
          </el-tooltip>
          <el-tooltip content="删除" placement="top" v-if="scope.row.userId !== 1">
            <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
              v-hasPermi="['system:user:remove']"></el-button>
          </el-tooltip>
          <el-tooltip content="重置密码" placement="top" v-if="scope.row.userId !== 1">
            <el-button link type="primary" icon="Key" @click="handleResetPwd(scope.row)"
              v-hasPermi="['system:user:resetPwd']"></el-button>
          </el-tooltip>
          <el-tooltip content="分配角色" placement="top" v-if="scope.row.userId !== 1">
            <el-button link type="primary" icon="CircleCheck" @click="handleAuthRole(scope.row)"
              v-hasPermi="['system:user:edit']"></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改客户信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="customerRef" :model="form" :rules="rules" label-width="80px">
        <!--  <el-row>
          <el-col :lg="2" :md="2">
            <el-form-item label="上传照片">
              <div class="custom-upload">
                <el-upload class="upload-demo" action="#" style="width: 200px" :on-change="handleFileChange"
                  :file-list="fileList" :auto-upload="false" accept="image/*" :disabled="false">
                  <el-button slot="trigger" type="primary" :disabled="false">
                    选择文件
                    <el-icon class="el-icon-upload" v-model="form.avatar"></el-icon>
                  </el-button>
                </el-upload>
                <img v-if="previewImage" :src="previewImage" alt="Preview" style="max-width: 100%; margin-top: 10px;">
              </div>
            </el-form-item>
          </el-col>
        </el-row> -->
        <el-form-item label="用户账号" prop="customerName">
          <el-input v-model="form.customerName" placeholder="请输入用户账号" />
        </el-form-item>
        <el-form-item label="用户昵称" prop="custname">
          <el-input v-model="form.custname" placeholder="请输入用户昵称" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="电话" prop="phonenumber">
          <el-input v-model="form.phonenumber" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择性别">
            <el-option v-for="dict in sys_user_sex" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" show-password />
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="状态" prop="status">
              <el-radio-group v-model="form.status">
                <el-radio v-for="dict in sys_normal_disable" :key="dict.value" :label="dict.value">{{ dict.label
                  }}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="角色">
              <el-select v-model="form.roleIds" multiple placeholder="请选择">
                <el-option v-for="item in roleOptions" :key="item.roleId" :label="item.roleName" :value="item.roleId"
                  :disabled="item.status == 1"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Customer">
import { listCustomer, getCustomer, delCustomer, addCustomer, updateCustomer, changeCustomerStatus } from "@/api/user/customer";
import UserAvatar from '@/views/system/user/profile/userAvatar.vue';
import { getToken } from "@/utils/auth";

const { proxy } = getCurrentInstance();
const { sys_user_sex, sys_normal_disable } = proxy.useDict('sys_user_sex', 'sys_normal_disable');

const customerList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const roleOptions = ref([]);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    customerName: null,
    custname: null,
    phonenumber: null,
    createTime: null,
    status: undefined,
  },
  rules: {
    customerName: [{ required: true, message: "用户账号不能为空", trigger: "blur" }, { min: 2, max: 12, message: "用户名称长度必须介于 8 和 12 之间", trigger: "blur" }],
    custname: [{ required: true, message: "用户昵称不能为空", trigger: "blur" }, { min: 2, max: 20, message: "用户名称长度必须介于 2 和 20 之间", trigger: "blur" }],
    password: [{ required: true, message: "用户密码不能为空", trigger: "blur" }, { min: 5, max: 20, message: "用户密码长度必须介于 5 和 20 之间", trigger: "blur" }],
    email: [{ type: "email", message: "请输入正确的邮箱地址", trigger: ["blur", "change"] }],
    phonenumber: [{ pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输入正确的手机号码", trigger: "blur" }]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询客户信息列表 */
function getList() {
  loading.value = true;
  listCustomer(queryParams.value).then(response => {
    customerList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}


// 处理头像更新
/** 头像上传 */
function handleAvatarUpdate(avatarUrl) {
  form.value.avatar = avatarUrl
}
function handleFileChange(file) {
  const selectedFile = file.raw;
  if (selectedFile) {
    const reader = new FileReader();
    reader.readAsDataURL(selectedFile);
    reader.onload = () => {
      previewImage.value = reader.result;

      const formData = new FormData();
      formData.append('avatarfile', selectedFile);

      uploadImg(formData)
        .then(response => {
          console.log('上传成功:', response.imgUrl);
          // 如果需要其他操作，可以在这里进行处理
          form.value.avatar = response.imgUrl
        })
        .catch(error => {
          console.error('上传失败:', error);
        });
    };
  }
  fileList.value = [file];
}

// 获取头像地址
const getAvatarUrl = (avatar) => {
  if (!avatar) {
    return '';
  }
  if (avatar.startsWith('http') || avatar.startsWith('https')) {
    return avatar;
  }
  return import.meta.env.VITE_APP_BASE_API + avatar;
}
// 头像加载失败时的处理方法
const handleAvatarError = function () {
  return true;
};

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    avatar: null,
    customerName: null,
    custname: null,
    userType: null,
    email: null,
    phonenumber: null,
    sex: null,
    password: null,
    status: null,
    balance: null,
    createTime: null,
    updateTime: null,
    createBy: null,
    updateBy: null,
    remark: null
  };
  proxy.resetForm("customerRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加客户信息";
  roleOptions.value = response.roles;
  form.value.password = initPassword.value;
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getCustomer(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改客户信息";
    roleOptions.value = response.roles;
    form.value.roleIds = response.roleIds;
    form.password = "";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["customerRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateCustomer(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addCustomer(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}
/** 用户状态修改  */
function handleStatusChange(row) {
  let text = row.status === "0" ? "启用" : "停用";
  proxy.$modal.confirm('确认要"' + text + '""' + row.customerName + '"用户吗?').then(function () {
    return changeCustomerStatus(row.id, row.status);
  }).then(() => {
    proxy.$modal.msgSuccess(text + "成功");
  }).catch(function () {
    row.status = row.status === "0" ? "1" : "0";
  });
};

/** 跳转角色分配 */
function handleAuthRole(row) {
  const userId = row.userId;
  router.push("/user/user-auth/role/" + Id);
};
/** 重置密码按钮操作 */
function handleResetPwd(row) {
  proxy.$prompt('请输入"' + row.customerName + '"的新密码', "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    closeOnClickModal: false,
    inputPattern: /^.{5,20}$/,
    inputErrorMessage: "用户密码长度必须介于 5 和 20 之间",
  }).then(({ value }) => {
    resetUserPwd(row.userId, value).then(response => {
      proxy.$modal.msgSuccess("修改成功，新密码是：" + value);
    });
  }).catch(() => { });
};

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除客户信息编号为"' + _ids + '"的数据项？').then(function () {
    return delCustomer(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => { });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('user/customer/export', {
    ...queryParams.value
  }, `customer_${new Date().getTime()}.xlsx`)
}

getList();
</script>
