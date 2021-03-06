package club.javalearn.admin.service.impl;

import club.javalearn.admin.common.BootstrapMessage;
import club.javalearn.admin.common.Message;
import club.javalearn.admin.entity.Permission;
import club.javalearn.admin.entity.Role;
import club.javalearn.admin.entity.info.RoleInfo;
import club.javalearn.admin.repository.PermissionRepository;
import club.javalearn.admin.repository.RoleRepository;
import club.javalearn.admin.service.RoleService;
import club.javalearn.admin.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author king-pan
 * Date: 2018/7/10
 * Time: 下午5:46
 * Description: No Description
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Message<Role> getPageList(RoleInfo roleInfo, Pageable pageable) {
        BootstrapMessage<Role> message = new BootstrapMessage<>();
        final Role role = roleInfo.convertRole();
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "updateTime"));
        sort.and(new Sort(Sort.Direction.ASC, "roleId"));
        sort.and(new Sort(Sort.Direction.ASC, "status"));

        PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Page<Role> rolePage = roleRepository.findAll(new Specification<Role>(){
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> roleNamePath = root.get("roleName");
                Path<String> roleCodePath = root.get("roleCode");
                Path<String> statusPath = root.get("status");
                List<Predicate> wherePredicate = new ArrayList<>();
                if(role!=null){
                    if(StringUtils.isNoneBlank(role.getRoleName())){
                        wherePredicate.add(cb.like(roleNamePath,"%"+role.getRoleName()+"%"));
                    }
                    if(StringUtils.isNoneBlank(role.getRoleCode())){
                        wherePredicate.add(cb.like(roleCodePath,"%"+role.getRoleCode()+"%"));
                    }
                    if(StringUtils.isNoneBlank(role.getStatus()) && !Constants.NOT_SELECT.equals(role.getStatus())){
                        wherePredicate.add(cb.equal(statusPath,role.getStatus()));
                    }
                }

                Predicate[] predicates = new Predicate[]{};
                //这里可以设置任意条查询条件
                if (CollectionUtils.isNotEmpty(wherePredicate)){
                    query.where(wherePredicate.toArray(predicates));
                }
                //这种方式使用JPA的API设置了查询条件，所以不需要再返回查询条件Predicate给Spring Data Jpa，故最后return null;即可。
                return null;
            }
        },pageRequest);

        message.setLimit(rolePage.getSize());
        message.setStart(rolePage.getNumber());
        message.setTotal(rolePage.getTotalElements());
        message.setRows(rolePage.getContent());
        log.debug("limit=" + rolePage.getSize() + ",total=" + rolePage.getTotalElements() + ",start=" + rolePage.getNumber() + ",numberOfElements=" + rolePage.getNumberOfElements());
        return message;
    }

    @Override
    public int deleteRoles(List<Long> roleIds) {
        return roleRepository.deleteRoleByIds(roleIds);
    }


    @Override
    public Role save(RoleInfo roleInfo) {
        Role role = roleInfo.convertRole();
        Role result;
        //修改
        if(role.getRoleId()!=null){
            Role r = roleRepository.getOne(role.getRoleId());
            r.setRemark(role.getRemark());
            r.setRoleCode(role.getRoleCode());
            r.setRoleName(role.getRoleName());
            r.setUpdateTime(new Date());
            r.setStatus(role.getStatus());
            result = roleRepository.save(r);
        }else{
            role.setCreateTime(new Date());
            role.setStatus(Constants.DEFAULT_STATUS);
            result = roleRepository.save(role);
        }
        return result;
    }



    @Override
    public Message<Role> getNoSelectRole(Long userId, String name, Pageable pageable) {
        BootstrapMessage<Role> message = new BootstrapMessage<>();
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "updateTime"));
        sort.and(new Sort(Sort.Direction.ASC, "status"));
        sort.and(new Sort(Sort.Direction.ASC, "roleId"));
        Page<Role> rolePage;
        if(StringUtils.isNoneBlank(name)){
            rolePage = roleRepository.getNoSelectRoleList(userId,name,pageable);
        }else{
            rolePage = roleRepository.getNoSelectRoleList(userId,pageable);
        }
        message.setTotal(rolePage.getTotalElements());
        message.setLimit(rolePage.getSize());
        message.setRows(rolePage.getContent());
        message.setStart(rolePage.getNumber());
        log.debug("limit=" + rolePage.getSize() + ",total=" + rolePage.getTotalElements() + ",start=" + rolePage.getNumber() + ",numberOfElements=" + rolePage.getNumberOfElements());
        return message;
    }

    @Override
    public void modifyStatus(Long roleId, String status) {
        roleRepository.modifyStatus(roleId,status);
    }

    @Override
    public void rolePermission(Long roleId, Long[] permissionIds) {
        Role role = roleRepository.getOne(roleId);
        Set<Permission> permissionSet = role.getPermissions();
        List<Permission> permissions = permissionRepository.findAllById(Arrays.asList(permissionIds));
        permissionSet.addAll(permissions);
        roleRepository.save(role);
    }
}
